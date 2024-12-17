package com.example.transportepublico.activity

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.transportepublico.R
import com.example.transportepublico.repository.DataRepository

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val paradasLayout = findViewById<LinearLayout>(R.id.paradasContainer)

        DataRepository.getParadasEnTiempoReal { paradas, error ->
            if (error != null) {
                Toast.makeText(this, "Error al cargar paradas: $error", Toast.LENGTH_LONG).show()
            } else {
                paradas?.forEach { parada ->
                    val paradaView = TextView(this).apply {
                        text = "Parada: ${parada.nombre}, Línea: ${parada.linea}, Horario: ${parada.horario.joinToString(", ")}"
                        setPadding(16, 16, 16, 16)
                    }
                    paradasLayout.addView(paradaView)
                }
            }
        }
    }
}
