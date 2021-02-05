package com.example.sites

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListaMiradores : AppCompatActivity() {

    var  miradores:Miradores = Miradores
    private lateinit var listamiradores: ListView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_miradores)
        listamiradores= findViewById(R.id.listamiradores)

        val adapter = ListaMiradoresAdapter(this, miradores)
        listamiradores.adapter = adapter

        listamiradores.onItemClickListener =
            OnItemClickListener { parent, view, position, id -> //position será el índice del elemento pulsado
                var miradorPulsado=listamiradores.getItemAtPosition(position) as String
                val intent: Intent = Intent(view.context, ActivityInfoMiradores::class.java)
                intent.putExtra("MIRADOR", miradorPulsado)
                intent.putExtra("POS", position.toString())
                startActivity(intent)

            }

        var botton = findViewById<View>(R.id.floatingActionButton) as FloatingActionButton
        botton?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                finish()
            }
        })



    }
}