package com.example.transportepublico.views

import android.content.Context
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.transportepublico.R
import com.example.transportepublico.dataClasses.Parada

class ParadaView(context: Context) : LinearLayout(context) {

    private val ivParadaIcon: ImageView
    private val tvNombreParada: TextView
    private val tvLinea: TextView
    private val tvHorario: TextView

    init {
        LayoutInflater.from(context).inflate(R.layout.item_parada, this, true)

        ivParadaIcon = findViewById(R.id.ivParadaIcon)
        tvNombreParada = findViewById(R.id.tvNombreParada)
        tvLinea = findViewById(R.id.tvLinea)
        tvHorario = findViewById(R.id.tvHorario)
    }

    fun setParada(parada: Parada) {
        ivParadaIcon.setImageResource(R.drawable.ic_stop)
        tvNombreParada.text = "Parada: ${parada.nombre}"
        tvLinea.text = "Líneas: ${parada.linea.joinToString(", ")}"
        tvHorario.text = "Horario: ${parada.horario.joinToString(", ")}"
    }
}
