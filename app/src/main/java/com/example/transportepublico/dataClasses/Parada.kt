package com.example.transportepublico.dataClasses

data class Parada(
    var idParada: String = "",
    val nombre: String = "",
    val lineas: List<Int> = listOf(),
    val horario: List<String> = listOf()
)
