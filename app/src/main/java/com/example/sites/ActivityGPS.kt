package com.example.sites

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_gps.*
import java.util.jar.Manifest

class ActivityGPS : AppCompatActivity(), OnMapReadyCallback {

    var tvMensaje: TextView?=null
    val MIN_TIME: Long=10000
    var local:Localizacion?=null
    var locationManager:LocationManager?=null

    //Parte de Fragmentmaps
    var lat: Double=0.0
    var lon: Double=0.0




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gps)
        tvMensaje = findViewById(R.id.tvMensaje)

        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),1000)
        }else{
            iniciarLocalizacion()
        }
    }

    fun iniciarLocalizacion(){
        locationManager= getSystemService(Context.LOCATION_SERVICE) as LocationManager
        local = Localizacion(this)

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
        locationManager?.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, MIN_TIME, 0F, local as LocationListener)

        tvMensaje?.setText("Localización Agregada")

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode==1000){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                iniciarLocalizacion()
                return
            }
        }
    }

    override fun onResume() {
        super.onResume()


        var contextAct:ActivityGPS=this
        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        // Create a listener
        val gyroscopeSensorListener = object : SensorEventListener {
            var out: Boolean = false
            override fun onSensorChanged(sensorEvent: SensorEvent) {
                if (sensorEvent.values[0]<0.5 ) {
                    if (ActivityCompat.checkSelfPermission(
                            contextAct,
                            android.Manifest.permission.ACCESS_FINE_LOCATION
                        ) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(
                            contextAct,
                            android.Manifest.permission.ACCESS_COARSE_LOCATION
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {
                        ActivityCompat.requestPermissions(
                            contextAct,
                            arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                            1000
                        )
                    } else {
                        tvMensaje?.setText("Actualizando ")
                        locationManager?.removeUpdates(local as LocationListener)
                        locationManager?.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME, 0F, local as LocationListener)
                        locationManager?.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME, 0F, local as LocationListener)
                        locationManager?.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, MIN_TIME, 0F, local as LocationListener)
                        if(locationManager?.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)!=null){
                            local?.onLocationChanged(locationManager?.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)!!)
                            tvMensaje?.setText("Actualizando..."+ tvMensaje?.text.toString())
                        }
                    }
                }
            }

            override fun onAccuracyChanged(sensor: Sensor, i: Int) {}
        }
        sensorManager.registerListener(gyroscopeSensorListener,gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL)
    }





    //Parte de mapFragment

    //Se llama desde Localización
    fun onCreateView(lati: Double, longi: Double){
        //Fragment del mapa
        var fragment = SupportMapFragment()

        var bundle= Bundle()
        bundle.putDouble("lat",lat)
        bundle.putDouble("lon",lon)
        fragment.arguments = bundle


        var fragmentManager: FragmentManager =this.supportFragmentManager
        var fragmentTransaction: FragmentTransaction =fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragment,fragment, null)
        fragmentTransaction.commitAllowingStateLoss()


        if(fragment.arguments!=null){
            this.lat=lati
            this.lon=longi
        }

        fragment.getMapAsync(this)
    }

    //Viene de OnMapCallBack
    override fun onMapReady(googleMap: GoogleMap?) {
        var latLng = LatLng(lat, lon)
        var zoom: Float = 17F

        if (googleMap != null) {

            //Modo noche
            //googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.style_dark_json))

            //Modo normal
            googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.style_json))

            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom))

            googleMap.uiSettings.isZoomControlsEnabled=false
            googleMap.uiSettings.isCompassEnabled=true


            googleMap.addMarker(MarkerOptions().position(latLng))

        }
    }


}