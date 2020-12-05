package com.example.sites

import android.location.Location
import android.location.LocationProvider
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.location.LocationListener

class Localizacion(activity: ActivityGPS): LocationListener {

    var ActivityGPS = activity
    var tvMensaje: TextView? =null

    override fun onLocationChanged(location: Location) {
        //este método se ejecuta cuando el gps recibe nuevas coordenadas
        var texto="Mi ubicación es: \nLatitud = ${location.latitude}\nLongitud = ${location.longitude}"
        tvMensaje?.text = texto

        ActivityGPS.onCreateView(location.latitude,location.longitude)
    }


    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?){
        if(status== LocationProvider.AVAILABLE){
            Log.d("debug","LocationProvider.AVAILABLE")
        }else if(status== LocationProvider.OUT_OF_SERVICE){
            Log.d("debug","LocationProvider.OUT_OF_SERVICE")
        }else if(status== LocationProvider.TEMPORARILY_UNAVAILABLE){
            Log.d("debug","LocationProvider.TEMPORARILY_UNAVAILABLE")
        }
    }

    override fun onProviderEnabled(provider: String) {
        tvMensaje?.text="GPS Activado"
    }

    override fun onProviderDisabled(provider: String) {
        tvMensaje?.text="GPS Desactivado"
    }

}