package com.example.transportepublico.dataClasses

data class Parada(
    val idParada: String = "",
    val nombre: String = "",
    val lineas: List<Int> = listOf(),
    val horario: List<String> = listOf()
)
