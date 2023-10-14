package com.example.traintravel

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
            var tanggalKeberangkatan = "null"

            val intent = intent
            val bundle = intent.extras
            tanggalKeberangkatan = bundle?.getString("selectedDate").toString()
            val stasiunAwal = bundle?.getString("stasiunAwal")
            val stasiunTujuan = bundle?.getString("stasiunTujuan")
            val kelas = bundle?.getString("kelas")
            val paket = bundle?.getStringArray("paket")

            txtStasiunAsal.text = stasiunAwal
            txtStasiunTujuan.text = stasiunTujuan
            txtTanggal.text = tanggalKeberangkatan
            txtKelas.text = kelas

            if (paket != null) {
                val paketText = paket.joinToString(separator = "; ")
                txtPaket.text = paketText
            }

            if (tanggalKeberangkatan != "null") {
                latestTravelNoPlan.visibility = View.GONE
                latestTravelPlan.visibility = View.VISIBLE
            }

            if (txtPaket.text == "") {
                txtPaket.text = "Anda tidak memilih paket tambahan"
            }

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