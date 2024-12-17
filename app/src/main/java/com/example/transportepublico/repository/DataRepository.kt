package com.example.transportepublico.repository

import com.example.transportepublico.dataClasses.Parada
import com.google.firebase.firestore.FirebaseFirestore

object DataRepository {
    private val db = FirebaseFirestore.getInstance()

    fun getParadasEnTiempoReal(callback: (List<Parada>?, String?) -> Unit) {
        db.collection("paradas").addSnapshotListener { snapshots, e ->
            if (e != null) {
                callback(null, e.message)
            } else {
                // Mapear los documentos de Firebase a objetos Parada
                val paradas = snapshots?.documents?.map { document ->
                    val parada = document.toObject(Parada::class.java)
                    parada?.let {
                        it.idParada = document.id
                        return@map it  // Devuelve el objeto parada modificado
                    }
                    return@map null
                }?.filterNotNull() // Elimina los nulos que puedan quedar en la lista

                callback(paradas, null)
            }
        }
    }
}