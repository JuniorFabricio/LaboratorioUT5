package com.example.laboratoriout5

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetalleActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)
        val txtTitulo = findViewById<TextView>(R.id.txtTituloDetalle)
        val txtPeligro = findViewById<TextView>(R.id.txtPeligroDetalle)
        val btnVolver = findViewById<Button>(R.id.btnVolver)

        val nombre = intent.getStringExtra("NOMBRE_ETAPA") ?: "Desconocido"
        val peligro = intent.getStringExtra("PELIGROSIDAD_ETAPA") ?: "Desconocido"

        txtTitulo.text = nombre
        txtPeligro.text = "Nivel de Peligro: $peligro"
        btnVolver.setOnClickListener {
            finish()
        }
    }
}