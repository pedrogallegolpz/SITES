package com.example.sites

import android.R.attr.*
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.gesture.Gesture
import android.gesture.GestureLibraries
import android.gesture.GestureLibrary
import android.gesture.GestureOverlayView
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Math.*


class MainActivity : AppCompatActivity(), GestureOverlayView.OnGesturePerformedListener,
    SensorEventListener {

    //Previene pulsar cualquier boton mientras se hace el gesto
    private var gLibrary: GestureLibrary? = null

    // Variables de cámara
    private var cam = Camera(this)


    // record the compass picture angle turned
    private var currentDegree = 0f

    // device sensor manager
    private var mSensorManager: SensorManager? = null
    var gyroscopeSensor:Sensor? = null
    var accelerometerSensor:Sensor? = null

    // image compass
    private var imview: ImageView?=null
    private var destimview: ImageView?=null

    val MIN_TIME: Long=100000
    var locationManager:LocationManager?=null
    var longit:Double=0.0
    var latit:Double= 0.0
    var latlonActualizadas:Boolean=false
    var enMirador:Boolean=false
    var miradorElegido:String = ""
    var posicionElegido:Int = -1

    var m=Miradores
    var mon = Monumentos
    var  degreeAnt :Float =-1.0f

    // Movimiento
    var gyroscopeAnt = Array<Float>(3, {i -> 0.0f})
    var compl = Array(4){ i -> false }

    // Como llegar
    var mirador_destino: Int=-1
    var z = Zona

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cam.onCreate()

        initData()


        if(ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )!= PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                1000
            )
        }else{
            iniciarLocalizacion()
        }

        if(intent.hasExtra("POS")){
            var position = intent.getStringExtra("POS").toString()
            comoLlegar(position.toInt())
            gestureSetup()
            val action = "Si se encuentra en un mirador haga un rayo para obtener información"
            val notificacion:Toast = Toast.makeText(this, action, Toast.LENGTH_LONG)
            notificacion.setGravity(Gravity.CENTER,Gravity.CENTER,Gravity.CENTER)
            notificacion.show()
        }else{
            gestureSetup()
            val action = "Si se encuentra en un mirador haga un rayo para obtener información"
            val notificacion:Toast = Toast.makeText(this, action, Toast.LENGTH_LONG)
            notificacion.setGravity(Gravity.CENTER,Gravity.CENTER,Gravity.CENTER)
            notificacion.show()

        }

        var botton = findViewById<View>(R.id.floatingActionButton2) as FloatingActionButton
        botton?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                val intent: Intent = Intent(v.context, MenuPrincipal::class.java)
                startActivity(intent)
            }
        })

        gyroscopeSensor = mSensorManager?.getDefaultSensor(Sensor.TYPE_ORIENTATION)
        accelerometerSensor = mSensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


    }

    fun iniciarLocalizacion(){
        locationManager= getSystemService(Context.LOCATION_SERVICE) as LocationManager


        val gpsEnabled: Boolean= locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER)
        if(!gpsEnabled){
            var intent: Intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            startActivity(intent)
        }

        if(ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )!= PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                1000
            )

            return;
        }
        locationManager?.requestLocationUpdates(
            LocationManager.NETWORK_PROVIDER,
            MIN_TIME,
            0F,
            locationListener
        )

        locationListener.onLocationChanged(locationManager?.getLastKnownLocation(LocationManager.NETWORK_PROVIDER) as Location)


    }

    //define the location listener
    private val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {

            longit=location.longitude
            latit=location.latitude

            calcularZona()
            latlonActualizadas=true
        }
        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }

    private fun gestureSetup(){
        gLibrary= GestureLibraries.fromRawResource(this, R.raw.gesture)

        if(gLibrary?.load()==false){
            finish()
        }
        gOverlay.setGestureVisible(false);
        gOverlay.addOnGesturePerformedListener(this)
    }

    override fun onGesturePerformed(overlay: GestureOverlayView?, gesture: Gesture?) {

        val predictions = gLibrary?.recognize(gesture)

        predictions?.let {
            if(it.size > 0 && it[0].score > 2.0){


                //val intent: Intent = Intent(this, ActivityGPS::class.java)
                //startActivity(intent)

                /** Cambio a que el rayo nos de información */

                if(enMirador){
                    val intent: Intent = Intent(this, ActivityInfoMiradores::class.java)
                    intent.putExtra("MIRADOR", miradorElegido)
                    intent.putExtra("POS", posicionElegido.toString())
                    startActivity(intent)

                    val action = "cambiando a información"
                    Toast.makeText(this, action, Toast.LENGTH_SHORT).show()
                }else{
                    val action = "debe estar en un mirador"
                    Toast.makeText(this, action, Toast.LENGTH_SHORT).show()
                }

                this.overridePendingTransition(
                    android.R.anim.slide_in_left,
                    android.R.anim.slide_out_right
                )
            }
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == cam.getRequestCameraPermission()) {
            if (grantResults.size>0 && grantResults[0] == PackageManager.PERMISSION_DENIED) {
                // close the app
                Toast.makeText(
                    this@MainActivity,
                    "Sorry!!!, you can't use this app without granting permission",
                    Toast.LENGTH_LONG
                ).show()
                finish()
            }
        }
        if(requestCode==1000){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                iniciarLocalizacion()
                return
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Log.e(cam.getTAG(), "onResume")
        cam.startBackgroundThread()
        if (cam.textureView!!.isAvailable) {
            cam.openCamera()
        } else {
            cam.onResume()
        }

        mSensorManager?.registerListener(
            this,
            mSensorManager?.getDefaultSensor(Sensor.TYPE_ORIENTATION),
            SensorManager.SENSOR_DELAY_GAME
        )

        mSensorManager?.registerListener(this, gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL)
        mSensorManager?.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        Log.e(cam.getTAG(), "onPause")
        cam.stopBackgroundThread()
        super.onPause()
    }

    fun anguloLatLon(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double{
        return Math.atan2(lat1 - lat2, lon1 - lon2)

    }

    override fun onSensorChanged(event: SensorEvent?) {
        val degree=Math.round(event?.values?.get(0)!!)
        val degree_float=event?.values?.get(0) as Float
        val tit : TextView = findViewById(R.id.textView5)
        tit.text=compl[0].toString()+compl[1].toString()+compl[2].toString()+compl[3].toString()

        if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            var valorAccelerometerX = event.values[0]
            var valorAccelerometerY = event.values[1]
            var valorAccelerometerZ = event.values[2]

            if(abs(valorAccelerometerZ) > 20 && (abs(valorAccelerometerX) > 15 || abs(valorAccelerometerY) > 15)) {
                compl[0] = true
                if (abs(valorAccelerometerZ) > 20 && (abs(valorAccelerometerX) > 10 || abs(valorAccelerometerY) > 10)  && compl[1] == true) {
                    compl[2] = true
                }
            }
        }
        if (event.sensor.type == Sensor.TYPE_ORIENTATION) {
            var angle = Array<Float>(3, {i -> 1.0f})
                angle[0] += event.values[0]

            if(Math.abs(gyroscopeAnt[0]-angle[0])>90) {
                if (compl[0]) {
                    compl[1] = true

                    if (compl[2]){
                        var compl = Array(4){ i -> false }
                        val intent: Intent = Intent(this, ActivityAyuda::class.java)
                        startActivity(intent)
                    }
                }
            }

            gyroscopeAnt[0]=angle[0]

        }

        //necesito que ejecute si la diferencia de giro es suficiente

        if(degreeAnt==-1.0f || Math.abs(degree_float-degreeAnt)>0.5) {

            if (mirador_destino >= 0f && mirador_destino < m.arrayNombres.size) {
                var angulo: Double = anguloLatLon(
                    m.arraySitios[mirador_destino].lat,
                    m.arraySitios[mirador_destino].lon,
                    latit,
                    longit
                )
                angulo = -angulo + kotlin.math.PI / 2
                if (angulo < 0)
                    angulo = angulo + 2 * kotlin.math.PI
                angulo = 360 * angulo / (2 * kotlin.math.PI)


                var rotateAnimation = RotateAnimation(
                    currentDegree + angulo.toFloat(),
                    -degree.toFloat() + angulo.toFloat(),
                    Animation.RELATIVE_TO_SELF,
                    0.5f,
                    Animation.RELATIVE_TO_SELF,
                    0.5f
                )

                rotateAnimation.duration = 210
                rotateAnimation.fillAfter = true

                imview?.startAnimation(rotateAnimation)

                //Miramos si hemos llegado al mirador (estar a menos de 10 metros)
                var distMir = getDistanceFromLatLonInKm(
                    latit,
                    longit,
                    m.arraySitios[mirador_destino].lat,
                    m.arraySitios[mirador_destino].lon
                )

                destino.text =
                    "Dirigiéndote a " + m.arrayNombres[mirador_destino] + "(" + kotlin.math.truncate(
                        distMir * 1000
                    ).toInt() + "m )"
                if (distMir < 0.01) {
                    destinoAlcanzado()
                }
            } else {
                // Intentar ocultar la brújula
                imview?.animation?.cancel()
                imview?.animation = null

                //hacemos invisible la brújula
                if (imview?.visibility == View.VISIBLE) {
                    imview?.visibility = View.INVISIBLE
                }
            }

            currentDegree = (-degree).toFloat()
            if (latlonActualizadas) {
                mirandoHacia(currentDegree)
            }

        }
        degreeAnt=degree_float
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    private fun initData(){
        mSensorManager=getSystemService(Context.SENSOR_SERVICE) as SensorManager?
        imview=findViewById(R.id.imgCompass)
    }


    fun getDistanceFromLatLonInKm(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        var R: Double = 6371.0; // Radius of the earth in km
        var dLat = deg2rad(lat2 - lat1);  // deg2rad below
        var dLon = deg2rad(lon2 - lon1);
        var a =
            Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                    Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) *
                    Math.sin(dLon / 2) * Math.sin(dLon / 2)
        ;
        var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        var d = R * c; // Distance in km
        return d;
    }

    fun deg2rad(deg: Double): Double {
        return deg * (Math.PI/180)
    }



    fun comoLlegar(indice: Int){
        mirador_destino = indice


        destimview?.setImageResource(m.image_dest[mirador_destino])
        if(imview?.visibility==View.INVISIBLE) {
            imview?.visibility = View.VISIBLE
        }
    }

    fun destinoAlcanzado(){
        mirador_destino=-1
    }



    fun mirandoHacia(deg: Float){


        var z=Zona
        val m_cercanos = mutableMapOf<Double, Int>()

        var degg=-deg
        var degrad=toRadians(degg.toDouble())- kotlin.math.PI/2
        if(degrad> kotlin.math.PI){
            degrad=degrad - 2* kotlin.math.PI
        }
        degrad=-degrad

        //Si no estamos en un mirador, añadimos si estamos mirando hacia algún mirador
        if(!enMirador){
            var j = 0
            /* MONUMENTOS */
            var monumentoPulsado = ""
            var monumento = findViewById<View>(R.id.monumento) as FloatingActionButton
            monumento?.visibility=View.INVISIBLE
            for(monument in mon.arraySitios) {
                var angulo: Double = anguloLatLon(monument.lat, monument.lon, latit, longit)
                var distMon=getDistanceFromLatLonInKm(latit, longit, monument.lat, monument.lon)

                var degrad3 = degrad
                if(degrad*angulo<0 && abs(degrad - angulo)>kotlin.math.PI) {
                    if(degrad<0){
                        degrad3=degrad3+2* kotlin.math.PI
                    }else{
                        angulo=angulo+2* kotlin.math.PI
                    }
                }

                if ((abs(degrad3 - angulo) < PI / (distMon * 10)) && kotlin.math.truncate(distMon * 1000).toInt() < 30) {
                    monumento?.visibility=View.VISIBLE
                    monumentoPulsado = mon.arrayNombres[j]
                }

                j += 1
            }

            monumento?.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View) {
                    val intent: Intent =   Intent(v.context, ActivityInfoMiradores::class.java)
                    intent.putExtra("MIRADOR", monumentoPulsado)
                    intent.putExtra("POS", mon.getIndex(monumentoPulsado).toString())
                    intent.putExtra("MONUMENTO", "")
                    startActivity(intent)
                }
            })

            var indiceMir:Int=0
            for(mir in m.arraySitios) {
                var angulo: Double = anguloLatLon(mir.lat, mir.lon, latit, longit)
                var distMir=getDistanceFromLatLonInKm(latit, longit, mir.lat, mir.lon)

                var degrad2=degrad
                if(degrad*angulo<0 && abs(degrad - angulo)>kotlin.math.PI) {
                    if(degrad<0){
                        degrad2=degrad2+2* kotlin.math.PI
                    }else{
                        angulo=angulo+2* kotlin.math.PI
                    }
                }

                if (abs(degrad2 - angulo) < PI / (distMir * 10)) {
                    //mirandoa.text =mirandoa.text.toString() + m.arrayNombres[indiceMir] + System.lineSeparator()
                    m_cercanos.put(distMir, indiceMir)
                }

                indiceMir = indiceMir + 1
            }

            var distOrdenados=m_cercanos.toSortedMap()
            var indices_cercanos : ArrayList<Int> = ArrayList<Int>(distOrdenados.size)
            var distancias : ArrayList<Int> = ArrayList<Int>(distOrdenados.size)
            var position=-1

            if(intent.hasExtra("POS")){
                position = intent.getStringExtra("POS").toString().toInt()
                indices_cercanos.add(position)
                var dist = getDistanceFromLatLonInKm(latit, longit, m.arraySitios[position].lat, m.arraySitios[position].lon)
                distancias.add(kotlin.math.truncate(dist*1000).toInt())
            }

            for(i in distOrdenados.keys) {
                if(m_cercanos[i] as Int != position){
                    indices_cercanos.add(m_cercanos[i] as Int)
                    distancias.add(kotlin.math.truncate( i * 1000 ).toInt())
                }

            }

            val listamirandoa : ListView = findViewById(R.id.listamirandoa)
            val titulo : TextView = findViewById(R.id.textView4)
            titulo.text="   Miradores en esa dirección (" + indices_cercanos.size.toString() + ")"
            val adapter : ListaMirandoaAdapter
            if(intent.hasExtra("POS")){
                adapter = ListaMirandoaAdapter(this, indices_cercanos, distancias, true,true) //true para miradores
            }else{
                adapter = ListaMirandoaAdapter(this, indices_cercanos, distancias, true,false) //true para miradores
            }
            listamirandoa.adapter = adapter

            listamirandoa.onItemClickListener =
                AdapterView.OnItemClickListener { parent, view, position, id -> //position será el índice del elemento pulsado
                    var miradorPulsado = listamirandoa.getItemAtPosition(position) as String
                    val intent: Intent = Intent(view.context, ActivityInfoMiradores::class.java)
                    intent.putExtra("MIRADOR", miradorPulsado)
                    intent.putExtra("POS", indices_cercanos[position].toString())
                    startActivity(intent)

                }





        }else{
            //En caso de que estemos en un mirador, sólo se escribirían las zonas a las que miramos
            //Variable para llevar el índice del mirador en el array de zonas
            var numMirador:Int=0
            var zonasCercanas:ArrayList<Int> = ArrayList<Int>(15)
            var distancias:ArrayList<Int> = ArrayList<Int>(15)
            var position:Int
            if(intent.hasExtra("POS")){
                position = intent.getStringExtra("POS").toString().toInt()
                zonasCercanas.add(position)
                var dist = getDistanceFromLatLonInKm(latit, longit, m.arraySitios[position].lat, m.arraySitios[position].lon)
                distancias.add(kotlin.math.truncate(dist*1000).toInt())
            }
            for(zone in z.arrayZonas) {

                var zonaDetectada=false
                for(punto in 0..3){
                    var ang1: Double = anguloLatLon(zone[punto].x, zone[punto].y, latit, longit)
                    var ang2: Double = anguloLatLon(
                        zone[(punto + 1) % 4].x,
                        zone[(punto + 1) % 4].y,
                        latit,
                        longit
                    )

                    var angmayor: Double= 0.0
                    var angmenor: Double = 0.0

                    if (ang1 >= ang2) {
                        angmayor = ang1
                        angmenor = ang2
                    }else {
                        angmayor = ang2
                        angmenor = ang1
                    }

                    // con esta condición vemos si es negativo el número
                    if(!zonaDetectada) {
                        if (angmayor * angmenor < 0 && (angmayor-angmenor)> kotlin.math.PI) {
                            if ((degrad > angmayor && degrad <= kotlin.math.PI) || ( degrad < angmenor && degrad >= -kotlin.math.PI)) {
                                //mirandoa.text = mirandoa.text.toString() + z.arrayNombres[numMirador] + System.lineSeparator()
                                zonasCercanas.add(numMirador)
                                zonaDetectada = true
                            }
                        } else if ( degrad < angmayor && degrad > angmenor ) {
                            //mirandoa.text = mirandoa.text.toString() + z.arrayNombres[numMirador] + System.lineSeparator()
                            zonasCercanas.add(numMirador)
                            zonaDetectada = true
                        }
                    }
                }

                numMirador = numMirador + 1
            }
/*
            val listamirandoa : ListView = findViewById(R.id.listamirandoa)
            val titulo : TextView = findViewById(R.id.textView4)
            titulo.text="   Zonas en esa dirección"

            val adapter : ListaMirandoaAdapter
            if(intent.hasExtra("POS")){
                adapter = ListaMirandoaAdapter(this, zonasCercanas, distancias, false, true) //false para zonas y arrayList no se usa, inicializado a lo que sea
            }else{
                adapter = ListaMirandoaAdapter(this, zonasCercanas, distancias, false, false) //false para zonas y arrayList no se usa, inicializado a lo que sea
            }
            listamirandoa.adapter = adapter

            listamirandoa.onItemClickListener =
                AdapterView.OnItemClickListener { parent, view, position, id -> //position será el índice del elemento pulsado
                    var miradorPulsado = listamirandoa.getItemAtPosition(position) as String
                    val intent: Intent = Intent(view.context, ActivityInfoMiradores::class.java)
                    intent.putExtra("MIRADOR", miradorPulsado)
                    intent.putExtra("POS", zonasCercanas[position].toString())
                    intent.putExtra("ZONA", "")
                    startActivity(intent)

                }
*/
            /* READAPTANDO VISUAL ZONA */

            /*- primero readapto posiciones*/
            val listamirandoa : ListView = findViewById(R.id.listamirandoa)
            val titulo : TextView = findViewById(R.id.textView4)
            val boton2 : FloatingActionButton = findViewById(R.id.floatingActionButton2) // es el que hay que hacer invisible
            val boton3 : FloatingActionButton = findViewById(R.id.floatingActionButton3) // es el que hay que hacer visible
            val constraintLayoutMain: ConstraintLayout  = findViewById(R.id.constraintLayoutMain)

            titulo.visibility=View.INVISIBLE
            listamirandoa.visibility=View.INVISIBLE
            constraintLayoutMain.maxHeight=220
            boton2.visibility=View.INVISIBLE
            boton3.visibility=View.VISIBLE
            boton3?.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View) {
                    val intent: Intent = Intent(v.context, MenuPrincipal::class.java)
                    startActivity(intent)
                }
            })
            /*- ahora la zona*/

            val listazona : ListView = findViewById(R.id.listazona)

            //Quiero coger la más cercana
            for (i in zonasCercanas){

            }

            val adapter : ListaMirandoaAdapter
            adapter = ListaMirandoaAdapter(this, zonasCercanas, distancias, false, false) //false para zonas y arrayList no se usa, inicializado a lo que sea
            listazona.adapter = adapter
        }

    }

    fun calcularZona(){
        var m=Miradores
        val a = Zona.Point(latit, longit)
        enMirador=false

        //Indice en el array de Miradores del mirador en el que estamos (si estamos en alguno)
        var indiceMirador:Int = 0

        //Primero calculamos si nos encontramos en un mirador para no mostrar la posición de otros miradores en el TextView
        //Si estamos a menos de 20 metros de un mirador, consideramos que estamos en él
        for (x in m.getArray()){
            var dist=getDistanceFromLatLonInKm(latit, longit, x.lat, x.lon)
            if(dist<0.020){
                enMirador=true
                miradorElegido = m.arrayNombres[indiceMirador]
                posicionElegido = indiceMirador

                // Comprobamos que no estemos de camino a un mirador
                if(mirador_destino<0f || mirador_destino >= m.arrayNombres.size) {
                    zona.text = m.arrayNombres[indiceMirador] + System.lineSeparator()
                }
                break
            }else if(!enMirador) {
                indiceMirador = indiceMirador + 1
            }
        }

        // Comprobamos que no estemos de camino a un mirador y que no estemos en uno
        if(!enMirador && (mirador_destino<0f || mirador_destino >= m.arrayNombres.size)) {

            if (z.isInside(z.centro, 4, a))
                zona.text = "Centro"
            else if (z.isInside(z.albaicin, 4, a))
                zona.text = "Albaicin"
            else if (z.isInside(z.alhambra, 4, a))
                zona.text = "Alhambra"
            else if (z.isInside(z.carreteraSierra, 4, a))
                zona.text = "Carretera de la Sierra"
            else if (z.isInside(z.cartuja, 4, a))
                zona.text = "Cartuja"
            else if (z.isInside(z.cerrillo, 4, a))
                zona.text = "Cerrillo de Maracena"
            else if (z.isInside(z.chana, 4, a))
                zona.text = "La Chana"
            else if (z.isInside(z.generalife, 4, a))
                zona.text = "Dehesa del Generalife"
            else if (z.isInside(z.norte, 4, a))
                zona.text = "Zona Norte"
            else if (z.isInside(z.plazaToros, 4, a))
                zona.text = "Plaza de Toros"
            else if (z.isInside(z.realejo, 4, a))
                zona.text = "Realejo"
            else if (z.isInside(z.sacromonte, 4, a))
                zona.text = "Sacromonte"
            else if (z.isInside(z.vega, 4, a))
                zona.text = "Vega de Granada"
            else if (z.isInside(z.zaidin, 4, a))
                zona.text = "Zaidín"
            else if (z.isInside(z.juventud, 4, a))
                zona.text = "Estadio de la Juventud"
            else
                zona.text = "Fuera de Granada"
        }
    }


}