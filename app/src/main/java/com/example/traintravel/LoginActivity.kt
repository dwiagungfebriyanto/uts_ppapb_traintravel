package com.example.traintravel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.traintravel.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            val intent = intent
            val bundle = intent.extras
            val username = bundle?.getString("username")
            val password = bundle?.getString("password")

            btnLogin.setOnClickListener {
                val usernameInput = edittextUsername.text.toString()
                val passwordInput = edittextPassword.text.toString()

                if (usernameInput != "" && passwordInput != "") {
                    if (usernameInput == username && passwordInput == password) {
                        val intentToDashboardActivity = Intent(this@LoginActivity, DashboardActivity::class.java)
                        val bundle = Bundle()
                        bundle.putString("username", username)
                        intentToDashboardActivity.putExtras(bundle)
                        startActivity(intentToDashboardActivity)
                    } else {
                        Toast.makeText(this@LoginActivity, "Username dan password salah!", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@LoginActivity, "Harap mengisi username dan password Anda.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}