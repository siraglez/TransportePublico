package com.example.transportepublico.repository

import com.example.transportepublico.dataClasses.Parada
import com.example.transportepublico.dataClasses.Linea
import com.google.firebase.firestore.FirebaseFirestore

object DataRepository {
    private val db = FirebaseFirestore.getInstance()

    fun getParadasEnTiempoReal(callback: (List<Parada>?, String?) -> Unit) {
        db.collection("paradas").addSnapshotListener { snapshots, e ->
            if (e != null) {
                callback(null, e.message)
            } else {
                val paradas = snapshots?.toObjects(Parada::class.java)
                callback(paradas, null)
            }
        }
    }

    fun getLineasEnTiempoReal(callback: (List<Linea>?, String?) -> Unit) {
        db.collection("lineas").addSnapshotListener { snapshots, e ->
            if (e != null) {
                callback(null, e.message)
            } else {
                val lineas = snapshots?.toObjects(Linea::class.java)
                callback(lineas, null)
            }
        }
    }
}
