package com.example.sites

import ListaMiradoresAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

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
                var miradorPulsado=listamiradores.getItemAtPosition(position)
                val intent: Intent = Intent(view.context, InformacionMirador::class.java)
                startActivity(intent)
            }

    }
}