package com.example.sites

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import kotlin.random.Random


class MenuPrincipal : AppCompatActivity(), SensorEventListener {

    // device sensor manager
    private var mSensorManager: SensorManager? = null
    var accelerometerSensor: Sensor? = null
    var accAnt = 0.0f
    var sensoreventlistener :SensorEventListener=this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_principal)
    }

    override fun onResume() {
        super.onResume()

        var sitesBot = findViewById<View>(R.id.chatbot) as TextView
        sitesBot?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                val intent: Intent = Intent(v.context, SitesBot::class.java)
                mSensorManager?.unregisterListener(sensoreventlistener)
                startActivity(intent)
            }
        })

        var vision = findViewById<View>(R.id.vision) as ImageButton
        vision?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                val intent: Intent = Intent(v.context, MainActivity::class.java)
                mSensorManager?.unregisterListener(sensoreventlistener)
                startActivity(intent)
            }
        })

        var mapa = findViewById<View>(R.id.mapa) as ImageButton
        mapa?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                val intent: Intent = Intent(v.context, ActivityGPS::class.java)
                mSensorManager?.unregisterListener(sensoreventlistener)
                startActivity(intent)
            }
        })

        var botonMiradores = findViewById<View>(R.id.listaMiradores) as ImageButton
        botonMiradores?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                val intent: Intent = Intent(v.context, ListaMiradores::class.java)
                mSensorManager?.unregisterListener(sensoreventlistener)
                startActivity(intent)
            }
        })

        var botonAyuda = findViewById<View>(R.id.ayuda) as TextView
        botonAyuda?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                val intent: Intent = Intent(v.context, ActivityAyuda::class.java)
                mSensorManager?.unregisterListener(sensoreventlistener)
                startActivity(intent)
            }
        })

        mSensorManager= getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometerSensor = mSensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        mSensorManager?.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL)


    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    override fun onSensorChanged(event: SensorEvent?) {
        var dif=Math.abs(accAnt-event!!.values[2]);
        if(dif>15.0f){
            mSensorManager?.unregisterListener(sensoreventlistener)
            val m:Miradores=Miradores
            val random = Random
            var r = random.nextInt(0,m.getCount())
            var miradorPulsado = m.arrayNombres[r]
            val intent: Intent = Intent(this, ActivityInfoMiradores::class.java)
            intent.putExtra("MIRADOR", miradorPulsado)
            intent.putExtra("POS", r.toString())
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
            startActivity(intent)
        }
        accAnt=event.values[2];
    }

    override fun finish() {
        super.finish()
        mSensorManager?.unregisterListener(sensoreventlistener)
    }
}