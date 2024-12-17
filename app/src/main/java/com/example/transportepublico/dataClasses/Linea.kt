package com.example.transportepublico.dataClasses

data class Linea(
    val idLinea: String = "",
    val numero: Int = 0,
    val paradas: List<String> = listOf()
)
