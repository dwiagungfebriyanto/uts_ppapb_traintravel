package com.example.traintravel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.traintravel.databinding.ActivityDashboardBinding
import com.example.traintravel.databinding.ActivityPlanInputBinding

class PlanInputActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlanInputBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlanInputBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            val stasiun = resources.getStringArray(R.array.stasiun)
            val adapterStasiun = ArrayAdapter<String>(this@PlanInputActivity, R.layout.spinner_item, stasiun)
            spinnerStasiunAsal.adapter = adapterStasiun
            spinnerStasiunTujuan.adapter = adapterStasiun

            val kelasKereta = resources.getStringArray(R.array.kelas_kereta)
            val adapterKelasKereta = ArrayAdapter<String>(this@PlanInputActivity, R.layout.spinner_item, kelasKereta)
            spinnerKelasKereta.adapter = adapterKelasKereta
        }
    }
}