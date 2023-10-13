package com.example.traintravel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.traintravel.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            val intent = intent
            val bundle = intent.extras
            val username = bundle?.getString("username")

            txtGreet.text= "Halo, $username!"

            btnAddNewPlan.setOnClickListener {
                val intentToPlanInputActivity = Intent(this@DashboardActivity, PlanInputActivity::class.java)
                startActivity(intentToPlanInputActivity)
            }
        }
    }
}