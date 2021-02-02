package com.example.sites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class ActivityTextoAyuda : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_texto_ayuda)

        var nombreElegido = intent.getStringExtra("NOMBRE").toString()
        var position = intent.getStringExtra("POS").toString()

        var text : TextView = findViewById(R.id.textView)
        var titulo : TextView = findViewById(R.id.titulo)

        titulo.text = "   AYUDA:  "+ nombreElegido

        if (position.toInt()==0){
            text.text="En SitesBot aportamos un bot"
        }else if(position.toInt()==1){
            text.text="Con visión podrás saber que es lo que estás viendo en un determinado momento"
        }else if(position.toInt()==2){
            text.text="En miradores podrás obtener información de cualquier mirador y hacer que se te indique el camino"
        }else if(position.toInt()==3){
            text.text="En el mapa podrás obtener información de cualquier mirador seleccionandolo en el mapa y hacer que se te indique el camino"
        }
    }
}