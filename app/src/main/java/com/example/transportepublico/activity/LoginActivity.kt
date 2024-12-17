package com.example.transportepublico.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.transportepublico.R
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        val emailField = findViewById<EditText>(R.id.etEmail)
        val passwordField = findViewById<EditText>(R.id.etPassword)
        val loginButton = findViewById<Button>(R.id.btnLogin)
        val registerButton = findViewById<Button>(R.id.btnRegister)

        loginButton.setOnClickListener {
            val email = emailField.text.toString().trim()
            val password = passwordField.text.toString().trim()
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, complete los campos", Toast.LENGTH_SHORT).show()
            } else {
                loginUser(email, password)
            }
        }

        registerButton.setOnClickListener {
            val email = emailField.text.toString().trim()
            val password = passwordField.text.toString().trim()
            if (email.isEmpty() || password.isEmpty() || password.length < 6) {
                Toast.makeText(this, "Por favor, complete los campos con una contraseña válida", Toast.LENGTH_SHORT).show()
            } else {
                registerUser(email, password)
            }
        }
    }

    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Error al iniciar sesión: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun registerUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Registro exitoso, inicie sesión", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Error al registrarse: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }
    }
}
