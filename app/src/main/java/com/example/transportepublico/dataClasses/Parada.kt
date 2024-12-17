package com.example.transportepublico.dataClasses

data class Parada(
    val idParada: String = "",
    val nombre: String = "",
    val linea: List<Int> = listOf(),
    val horario: List<String> = listOf()
)
