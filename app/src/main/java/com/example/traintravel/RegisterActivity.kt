package com.example.traintravel

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.example.traintravel.databinding.ActivityRegisterBinding
import java.util.Calendar

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    val calendar = Calendar.getInstance()
    val currentYear = calendar.get(Calendar.YEAR)
    var selectedDate = ""
    private var yearOfBirth = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            btnBirthdate.setOnClickListener {
                showDatePicker()
            }

            btnRegister.setOnClickListener {
                val email = edittextEmail.text.toString()
                val username = edittextUsername.text.toString()
                val password = edittextPassword.text.toString()

                if (email != "" && username != "" && selectedDate != "") {
                    if (currentYear - yearOfBirth >= 15) {
                        val intentToLoginActivity = Intent(this@RegisterActivity, LoginActivity::class.java)

                        val bundle = Bundle()
                        bundle.putString("email", email)
                        bundle.putString("username", username)
                        bundle.putString("password", password)
                        bundle.putString("birthDate", selectedDate)
                        intentToLoginActivity.putExtras(bundle)

                        startActivity(intentToLoginActivity)
                    } else {
                        Toast.makeText(this@RegisterActivity, "Maaf umur Anda belum mencukupi.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@RegisterActivity, "Harap melengkapi data yang diperlukan.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun showDatePicker() {
        val datePicker = DatePickerDialog(this, { _, year, month, dayOfMonth ->
            yearOfBirth = year
            selectedDate = "$dayOfMonth/${month + 1}/$year"
            binding.btnBirthdate.text = selectedDate
        }, 2023, 9, 14)
        datePicker.show()
    }
}