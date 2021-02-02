package com.example.sites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView

class ActivityAyuda : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ayuda)
        var listaAyuda : ListView = findViewById(R.id.listaayuda)
        val opciones = arrayOf("SitesBot","Visión","Miradores","Mapa")
        val images = arrayOf(R.drawable.agente,R.drawable.vision,R.drawable.miradores,R.drawable.mapa1)

        val adapter = ListaAyudaAdapter(this, opciones, images)
        listaAyuda.adapter = adapter

        listaAyuda.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id -> //position será el índice del elemento pulsado
                var numbrePulsado = listaAyuda.getItemAtPosition(position) as String
                val intent: Intent = Intent(view.context, ActivityTextoAyuda::class.java)
                intent.putExtra("NOMBRE", numbrePulsado)
                intent.putExtra("POS", position.toString())
                startActivity(intent)
            }

    }
}