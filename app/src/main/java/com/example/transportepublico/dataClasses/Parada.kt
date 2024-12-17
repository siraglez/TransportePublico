package com.example.transportepublico.dataClasses

data class Parada(
    val idParada: String = "",
    val nombre: String = "",
    val linea: Int = 0,
    val horario: List<String> = listOf()
)
