package com.example.sites

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class PaginaInicial : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pagina_inicial)
    }

    override fun onResume() {
        super.onResume()

        Handler().postDelayed({
            val intent: Intent = Intent(this, MenuPrincipal::class.java)
            startActivity(intent)
        }, 2000)

    }
}