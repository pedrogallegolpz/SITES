package com.example.sites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class FragmentMaps: SupportMapFragment(), OnMapReadyCallback{

    var lat: Double=0.0
    var lon: Double=0.0


    override fun onCreateView(p0: LayoutInflater, p1: ViewGroup?, p2: Bundle?): View? {
        var rootView=super.onCreateView(p0, p1, p2)

        if(getArguments()!=null){
            this.lat= arguments!!.getDouble("lat")
            this.lon=arguments!!.getDouble("lon")
        }

        getMapAsync(this)

        return rootView
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        var latLng = LatLng(lat,lon)
        var zoom: Float = 17F

        if (googleMap != null) {
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,zoom))

            googleMap.uiSettings.isZoomControlsEnabled=true

            googleMap.addMarker(MarkerOptions().position(latLng))

            var settings: UiSettings=googleMap.uiSettings

            settings.isZoomControlsEnabled=true
        }
    }
}