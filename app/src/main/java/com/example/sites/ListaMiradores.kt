package com.example.sites

import ListaMiradoresAdapter
import android.os.Bundle
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ListaMiradores : AppCompatActivity() {

    var  miradores:Miradores = Miradores
    private lateinit var listamiradores: ListView
    private lateinit var textview: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_miradores)
        listamiradores= findViewById(R.id.listamiradores)

        /*
        val adapter =   android.widget.ArrayAdapter(this, R.layout.list_miradores, miradores.arrayNombres)
        listamiradores.adapter = adapter
        //Aquí especificamos la acción que se hace en cuanto se pulsa uno de los ejercicios
        listamiradores.onItemClickListener =
            OnItemClickListener { parent, view, position, id -> //position será el índice del elemento pulsado
                textview.setText(listamiradores.getItemAtPosition(position) as String)
            }

         */

        val adapter = ListaMiradoresAdapter(this, miradores)
        listamiradores.adapter = adapter

    }
}