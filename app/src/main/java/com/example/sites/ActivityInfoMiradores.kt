package com.example.sites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class ActivityInfoMiradores : AppCompatActivity() {

    lateinit var miradorElegido :String
    var  miradores:Miradores = Miradores

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_miradores)

        miradorElegido = intent.getStringExtra("MIRADOR").toString()
        var position = intent.getStringExtra("POS").toString()

        var imagen :ImageView = findViewById(R.id.imageView2)
        var text :TextView= findViewById(R.id.text)

        text.text= miradores.info[position.toInt()]
        Picasso.get().load(miradores.image[position.toInt()] ).placeholder(R.drawable.sillamoro).into(imagen)

    }
}