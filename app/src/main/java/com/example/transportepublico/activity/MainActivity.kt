package com.example.transportepublico.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.transportepublico.R
import com.example.transportepublico.repository.DataRepository

class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lineasLayout = findViewById<LinearLayout>(R.id.lineasContainer)

        DataRepository.getLineasEnTiempoReal { lineas, error ->
            if (error != null) {
                Toast.makeText(this, "Error al cargar líneas: $error", Toast.LENGTH_LONG).show()
            } else {
                lineas?.forEach { linea ->
                    val lineaView = TextView(this).apply {
                        text = "Línea: ${linea.numero}, Paradas: ${linea.paradas.joinToString(", ")}"
                        setPadding(16, 16, 16, 16)
                    }
                    lineasLayout.addView(lineaView)
                }
            }
        }
    }
}
