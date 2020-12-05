package com.example.sites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.view.View


class MenuPrincipal : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_principal)
    }

    override fun onResume() {
        super.onResume()

        var sitesBot = findViewById<View>(R.id.chatbot) as Button
        sitesBot?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                val intent: Intent = Intent(v.context, SitesBot::class.java)
                startActivity(intent)
            }
        })

    }
}