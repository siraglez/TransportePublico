package com.example.transportepublico.repository

import com.example.transportepublico.dataClasses.Parada
import com.example.transportepublico.dataClasses.Usuario
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

    fun saveUsuario(usuario: Usuario, callback: (Boolean, String?) -> Unit) {
        db.collection("usuarios").document(usuario.idUsuario).set(usuario)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(true, null)
                } else {
                    callback(false, task.exception?.message)
                }
            }
    }

    fun getUsuario(idUsuario: String, callback: (Usuario?, String?) -> Unit) {
        db.collection("usuarios").document(idUsuario).get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val usuario = task.result?.toObject(Usuario::class.java)
                    callback(usuario, null)
                } else {
                    callback(null, task.exception?.message)
                }
            }
    }
}
