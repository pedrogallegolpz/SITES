package com.example.sites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso

class ActivityInfoMiradores : AppCompatActivity() {

    lateinit var miradorElegido :String
    var  miradores:Miradores = Miradores
    var monumentos:Monumentos =Monumentos
    var zona:Zona =Zona

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_miradores)

        if(intent.hasExtra("MONUMENTO")){

            miradorElegido = intent.getStringExtra("MIRADOR").toString()
            var position = intent.getStringExtra("POS").toString()

            var imagen :ImageView = findViewById(R.id.imageView2)
            var text :TextView= findViewById(R.id.text)

            text.text= monumentos.info[position.toInt()]
            Picasso.get().load(monumentos.image[position.toInt()] ).placeholder(R.drawable.sillamoro).into(imagen)

            var botton = findViewById<View>(R.id.floatingActionButton) as FloatingActionButton
            botton.visibility=View.INVISIBLE

        }else if (intent.hasExtra("ZONA")) {

            miradorElegido = intent.getStringExtra("MIRADOR").toString()
            var position = intent.getStringExtra("POS").toString()

            var imagen :ImageView = findViewById(R.id.imageView2)
            var text :TextView= findViewById(R.id.text)

            text.text= zona.info[position.toInt()]
            Picasso.get().load(zona.image[position.toInt()] ).placeholder(R.drawable.sillamoro).into(imagen)

            var botton = findViewById<View>(R.id.floatingActionButton) as FloatingActionButton
            botton.visibility=View.INVISIBLE

        }else{

            miradorElegido = intent.getStringExtra("MIRADOR").toString()
            var position = intent.getStringExtra("POS").toString()

            var imagen :ImageView = findViewById(R.id.imageView2)
            var text :TextView= findViewById(R.id.text)

            text.text= miradores.info[position.toInt()]
            Picasso.get().load(miradores.image[position.toInt()] ).placeholder(R.drawable.sillamoro).into(imagen)

            var botton = findViewById<View>(R.id.floatingActionButton) as FloatingActionButton
            botton?.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View) {
                    val intent: Intent = Intent(v.context, MainActivity::class.java)
                    intent.putExtra("POS", position)
                    val action = "Siga la dirección de la flecha"
                    val notificacion: Toast = Toast.makeText(v.context, action, Toast.LENGTH_SHORT)
                    notificacion.setGravity(Gravity.CENTER, Gravity.CENTER, Gravity.CENTER)
                    notificacion.show()
                    startActivity(intent)
                }
            })

        }



    }
}