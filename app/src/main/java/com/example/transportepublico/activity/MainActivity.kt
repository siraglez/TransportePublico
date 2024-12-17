package com.example.transportepublico.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.transportepublico.R
import com.example.transportepublico.repository.DataRepository
import com.example.transportepublico.views.ParadaView

class MainActivity : AppCompatActivity() {

    private val paradasSet = mutableSetOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val paradasContainer = findViewById<LinearLayout>(R.id.paradasContainer)
        val settingsButton = findViewById<Button>(R.id.btnSettings)

        // Botón para abrir SettingsActivity
        settingsButton.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

        // Obtener las paradas en tiempo real
        DataRepository.getParadasEnTiempoReal { paradas, error ->
            if (error != null) {
                Toast.makeText(this, "Error al cargar paradas: $error", Toast.LENGTH_LONG).show()
            } else {
                // Limpiar contenedor antes de agregar las nuevas paradas
                paradasContainer.removeAllViews()

                // Filtrar las paradas para que no se repitan
                paradas?.forEach { parada ->
                    // Verificar si la parada ya fue agregada (usamos el idParada para asegurarlo)
                    if (parada.idParada !in paradasSet) {
                        val paradaView = ParadaView(this)
                        paradaView.setParada(parada)
                        paradasContainer.addView(paradaView)
                        paradasSet.add(parada.idParada)
                    }
                }
            }
        }
    }
}
