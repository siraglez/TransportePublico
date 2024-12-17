package com.example.transportepublico.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.transportepublico.R
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        val usernameField = findViewById<EditText>(R.id.etEmail) // Ahora es nombre de usuario
        val passwordField = findViewById<EditText>(R.id.etPassword)
        val loginButton = findViewById<Button>(R.id.btnLogin)
        val registerButton = findViewById<Button>(R.id.btnRegister)

        loginButton.setOnClickListener {
            val username = usernameField.text.toString().trim()
            val password = passwordField.text.toString().trim()
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, complete los campos", Toast.LENGTH_SHORT).show()
            } else {
                loginUser(username, password)
            }
        }

        registerButton.setOnClickListener {
            val username = usernameField.text.toString().trim()
            val password = passwordField.text.toString().trim()
            if (username.isEmpty() || password.isEmpty() || password.length < 6) {
                Toast.makeText(this, "Por favor, complete los campos con una contraseña válida", Toast.LENGTH_SHORT).show()
            } else {
                registerUser(username, password)
            }
        }
    }

    private fun loginUser(username: String, password: String) {
        // Verificar si el usuario existe en la base de datos
        db.collection("usuarios").whereEqualTo("email", username)
            .get()
            .addOnSuccessListener { documents ->
                if (documents.isEmpty) {
                    Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show()
                } else {
                    val userDoc = documents.first()
                    val storedPassword = userDoc.getString("password")
                    if (storedPassword == password) {
                        // Inicio de sesión exitoso
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error al verificar usuario: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun registerUser(username: String, password: String) {
        val user = hashMapOf(
            "email" to username,
            "password" to password
        )

        db.collection("usuarios").add(user)
            .addOnSuccessListener {
                Toast.makeText(this, "Registro exitoso, inicie sesión", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error al registrarse: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
