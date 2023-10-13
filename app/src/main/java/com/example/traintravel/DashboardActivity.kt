package com.example.traintravel

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
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
            val year = bundle?.getInt("year")
            val month = bundle?.getInt("month")
            val dayOfMonth = bundle?.getInt("dayOfMonth")
            val tanggalKeberangkatan = bundle?.getString("selectedDate")
            val stasiunAwal = bundle?.getString("stasiunAwal")
            val stasiunTujuan = bundle?.getString("stasiunTujuan")
            val kelas = bundle?.getString("kelas")
            val paket = bundle?.getString("paket")

            txtStasiunAsal.text = stasiunAwal
            txtStasiunTujuan.text = stasiunTujuan
//            txtTanggal.text = tanggalKeberangkatan
//            txtKelas.text = kelas
//            txtPaketTambahan.text = paket

            calendarView.setOnDateChangeListener{_, year, month, dayOfMonth ->
                val selectedDate = "$dayOfMonth/${month+1}/$year"
                if (selectedDate == tanggalKeberangkatan) {
                    Toast.makeText(this@DashboardActivity, "Ada rencana perjalanan.", Toast.LENGTH_SHORT).show()
                }
            }

            btnAddNewPlan.setOnClickListener {
                val intentToPlanInputActivity = Intent(this@DashboardActivity, PlanInputActivity::class.java)
                startActivity(intentToPlanInputActivity)
            }
        }
    }
}