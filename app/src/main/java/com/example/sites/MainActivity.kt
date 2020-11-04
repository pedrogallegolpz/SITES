package com.example.sites

import android.content.Context
import android.content.Intent
import android.gesture.Gesture
import android.gesture.GestureLibraries
import android.gesture.GestureLibrary
import android.gesture.GestureOverlayView
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.provider.Settings
import android.util.Log
import android.view.TextureView
import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat
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
    // image compass
    private var imview: ImageView?=null


    var tvMensaje: TextView?=null
    val MIN_TIME: Long=1000000
    var local:Localizacion?=null
    var locationManager:LocationManager?=null
    var longit:Double=0.0
    var latit:Double= 0.0

    var posPrueba1lat:Double=37.159181
    var posPrueba1lon:Double=-3.608803
    var posPrueba2lat:Double=37.159698
    var posPrueba2lon:Double=-3.6065912

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cam.onCreate()

        initData()

        gestureSetup()

        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),1000)
        }else{
            iniciarLocalizacion()
            locationManager?.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, locationListener)
        }

    }

    private fun gestureSetup(){
        gLibrary= GestureLibraries.fromRawResource(this,R.raw.gesture)

        if(gLibrary?.load()==false){
            finish()
        }
        gOverlay.addOnGesturePerformedListener(this)
    }

    override fun onGesturePerformed(overlay: GestureOverlayView?, gesture: Gesture?) {

        val predictions = gLibrary?.recognize(gesture)

        predictions?.let {
            if(it.size > 0 && it[0].score > 1.0){
                val action = "cambiando a mapa"
                Toast.makeText(this, action, Toast.LENGTH_SHORT).show()

                val intent: Intent = Intent(this, ActivityGPS::class.java)
                startActivity(intent)
                /** Fading Transition Effect */
                this.overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
            }
        }

    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == cam.getRequestCameraPermission()) {
            if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                // close the app
                Toast.makeText(this@MainActivity, "Sorry!!!, you can't use this app without granting permission", Toast.LENGTH_LONG).show()
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

        @Suppress("DEPRECATION")
        mSensorManager?.registerListener(this,mSensorManager?.getDefaultSensor(Sensor.TYPE_ORIENTATION),SensorManager.SENSOR_DELAY_GAME)
    }

    override fun onPause() {
        Log.e(cam.getTAG(), "onPause")
        //closeCamera();
        cam.stopBackgroundThread()
        super.onPause()
    }

    override fun onSensorChanged(event: SensorEvent?) {
        val degree=Math.round(event?.values?.get(0)!!)

        val rotateAnimation = RotateAnimation(currentDegree,(-degree).toFloat(), Animation.RELATIVE_TO_SELF,0.5f,
            Animation.RELATIVE_TO_SELF,0.5f)
        rotateAnimation.duration=210
        rotateAnimation.fillAfter=true

        imview?.startAnimation(rotateAnimation)
        currentDegree= (-degree).toFloat()
        mirandoHacia(currentDegree)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    private fun initData(){
        mSensorManager=getSystemService(Context.SENSOR_SERVICE) as SensorManager?
        imview=findViewById(R.id.imgCompass)
    }


    fun iniciarLocalizacion(){
        locationManager= getSystemService(Context.LOCATION_SERVICE) as LocationManager
        local = Localizacion()

        local?.tvMensaje = tvMensaje

        val gpsEnabled: Boolean= locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER)
        if(!gpsEnabled){
            var intent: Intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            startActivity(intent)
        }

        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),1000)

            return;
        }
        locationManager?.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME, 0F, local as LocationListener)
        locationManager?.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME, 0F, local as LocationListener)

        ubicaciones?.setText("Localización Agregada")

    }

    //define the listener
    private val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            /*val sb = StringBuilder()
            val ubi = ("Longitud:" + location.longitude + " Latitud: " + location.latitude)
            sb.append(ubicaciones.text).append(System.lineSeparator()).append(ubi)
            ubicaciones.text=sb.toString()*/
            ubicaciones.text=("Longitud:" + location.longitude + " Latitud: " + location.latitude)
            longit=location.longitude
            latit=location.latitude
        }
        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }

    fun mirandoHacia(deg: Float){
        var angulo:Double=Math.atan2(sin(posPrueba1lon-longit)*cos(posPrueba1lat), cos(latit)*sin(posPrueba1lat)-sin(latit)*cos(posPrueba1lat)*cos(posPrueba1lon-longit))
        var degg=-deg
        var angulo2:Double=Math.atan2(sin(posPrueba2lon-longit)*cos(posPrueba2lat), cos(latit)*sin(posPrueba2lat)-sin(latit)*cos(posPrueba2lat)*cos(posPrueba2lon-longit))
        angulo+=PI
        angulo2+=PI
        if(abs(toRadians(degg.toDouble())-angulo)<PI/4){
            mirandoa.text="Mirando hacia Posicion 1"
        }else if(abs(toRadians(degg.toDouble())-angulo2)<PI/4){
            mirandoa.text = "Mirando hacia Posicion 2"
        }else
            mirandoa.text = ""
    }

}