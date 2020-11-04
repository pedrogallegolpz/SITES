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
import android.util.Log
import android.view.TextureView
import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.FrameLayout
import android.widget.ImageView

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cam.onCreate()

        initData()

        gestureSetup()
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
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    private fun initData(){
        mSensorManager=getSystemService(Context.SENSOR_SERVICE) as SensorManager?
        imview=findViewById(R.id.imgCompass)
    }
}