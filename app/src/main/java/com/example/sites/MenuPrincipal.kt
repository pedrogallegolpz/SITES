package com.example.sites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton


class MenuPrincipal : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_principal)
    }

    override fun onResume() {
        super.onResume()

        var sitesBot = findViewById<View>(R.id.chatbot) as ImageButton
        sitesBot?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                val intent: Intent = Intent(v.context, SitesBot::class.java)
                startActivity(intent)
            }
        })

        var vision = findViewById<View>(R.id.vision) as ImageButton
        vision?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                val intent: Intent = Intent(v.context, MainActivity::class.java)
                startActivity(intent)
            }
        })

        var mapa = findViewById<View>(R.id.mapa) as ImageButton
        mapa?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                val intent: Intent = Intent(v.context, ActivityGPS::class.java)
                startActivity(intent)
            }
        })

        var botonMiradores = findViewById<View>(R.id.listaMiradores) as ImageButton
        botonMiradores?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                val intent: Intent = Intent(v.context, ListaMiradores::class.java)
                startActivity(intent)
            }
        })

        var botonAyuda = findViewById<View>(R.id.ayuda) as ImageButton
        botonAyuda?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                val intent: Intent = Intent(v.context, ActivityAyuda::class.java)
                startActivity(intent)
            }
        })


    }
}