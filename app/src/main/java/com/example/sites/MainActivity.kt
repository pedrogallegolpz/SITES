package com.example.sites

import android.content.Intent
import android.gesture.Gesture
import android.gesture.GestureLibraries
import android.gesture.GestureLibrary
import android.gesture.GestureOverlayView
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), GestureOverlayView.OnGesturePerformedListener {

    //Previene pulsar cualquier boton mientras se hace el gesto
    private var gLibrary: GestureLibrary? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

                val intent = Intent(this, ActivityGPS::class.java)
                startActivity(intent)
            }
        }

    }
}