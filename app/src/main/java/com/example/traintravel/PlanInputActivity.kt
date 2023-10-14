package com.example.traintravel

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.traintravel.databinding.ActivityDashboardBinding
import com.example.traintravel.databinding.ActivityPlanInputBinding

class PlanInputActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlanInputBinding
    var selectedDate = ""
    var hargaStasiunAwal = 0
    var hargaStasiunTujuan = 0
    var hargaTiket = 0
    var hargaPaket = 0
    var hargaTotal = 0
    val bundle = Bundle()
    var paket = arrayOf<String>()


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

            btnTanggalKeberangkatan.setOnClickListener {
                showDatePicker()
            }

            toggleMakanSiang.setOnClickListener{
                if (toggleMakanSiang.isChecked) {
                    hargaPaket += 25000
                    paket = paket.plus("Makan siang")
                } else {
                    hargaPaket -= 25000
                    paket = paket.filterNot { it == "Makan siang" }.toTypedArray()
                }
                hargaTotal = hargaStasiunAwal + hargaStasiunTujuan + hargaTiket + hargaPaket
                txtPrice.text = "Rp. " + hargaTotal.toString()
            }

            togglePinggirJendela.setOnClickListener{
                if (togglePinggirJendela.isChecked) {
                    hargaPaket += 20000
                    paket = paket.plus("Duduk di pinggir jendela")
                } else {
                    hargaPaket -= 20000
                    paket = paket.filterNot { it == "Duduk di pinggir jendela" }.toTypedArray()
                }
                hargaTotal = hargaStasiunAwal + hargaStasiunTujuan + hargaTiket + hargaPaket
                txtPrice.text = "Rp. " + hargaTotal.toString()
            }

            toggleDudukDepan.setOnClickListener{
                if (toggleDudukDepan.isChecked) {
                    hargaPaket += 5000
                    paket = paket.plus("Duduk di depan")
                } else {
                    hargaPaket -= 5000
                    paket = paket.filterNot { it == "Duduk di depan" }.toTypedArray()
                }
                hargaTotal = hargaStasiunAwal + hargaStasiunTujuan + hargaTiket + hargaPaket
                txtPrice.text = "Rp. " + hargaTotal.toString()
            }

            toggleChargingStation.setOnClickListener{
                if (toggleChargingStation.isChecked) {
                    hargaPaket += 2000
                    paket = paket.plus("Charging station")
                } else {
                    hargaPaket -= 2000
                    paket = paket.filterNot { it == "Charging station" }.toTypedArray()
                }
                hargaTotal = hargaStasiunAwal + hargaStasiunTujuan + hargaTiket + hargaPaket
                txtPrice.text = "Rp. " + hargaTotal.toString()
            }

            toggleWifi.setOnClickListener{
                if (toggleWifi.isChecked) {
                    hargaPaket += 15000
                    paket = paket.plus("Wi-Fi")
                } else {
                    hargaPaket -= 15000
                    paket = paket.filterNot { it == "Wi-Fi" }.toTypedArray()
                }
                hargaTotal = hargaStasiunAwal + hargaStasiunTujuan + hargaTiket + hargaPaket
                txtPrice.text = "Rp. " + hargaTotal.toString()
            }

            toggleRuangMerokok.setOnClickListener{
                if (toggleRuangMerokok.isChecked) {
                    hargaPaket += 50000
                    paket = paket.plus("Ruang khusus merokok")
                } else {
                    hargaPaket -= 50000
                    paket = paket.filterNot { it == "Ruang khusus merokok" }.toTypedArray()
                }
                hargaTotal = hargaStasiunAwal + hargaStasiunTujuan + hargaTiket + hargaPaket
                txtPrice.text = "Rp. " + hargaTotal.toString()
            }

            toggleRuangAnak.setOnClickListener{
                if (toggleRuangAnak.isChecked) {
                    hargaPaket += 2500
                    paket = paket.plus("Ruang khusus anak")
                } else {
                    hargaPaket -= 2500
                    paket = paket.filterNot { it == "Ruang khusus anak" }.toTypedArray()
                }
                hargaTotal = hargaStasiunAwal + hargaStasiunTujuan + hargaTiket + hargaPaket
                txtPrice.text = "Rp. " + hargaTotal.toString()
            }

            var stasiunAwal = ""
            spinnerStasiunAsal.onItemSelectedListener =
                object  : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                        if (stasiun[position] == "Pasar Senen - Jakarta") {
                            hargaStasiunAwal = 20000
                        }
                        if (stasiun[position] == "Gambir - Jakarta") {
                            hargaStasiunAwal = 30000
                        }
                        if (stasiun[position] == "Sentolo - Yogyakarta") {
                            hargaStasiunAwal = 40000
                        }
                        stasiunAwal = stasiun[position]
                        bundle.putString("stasiunAwal", stasiunAwal)
                        hargaTotal = hargaStasiunAwal + hargaStasiunTujuan + hargaTiket + hargaPaket
                        txtPrice.text = "Rp. " + hargaTotal.toString()
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }
                }

            var stasiunTujuan = ""
            spinnerStasiunTujuan.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                        if (stasiun[position] == "Pasar Senen - Jakarta") {
                            hargaStasiunTujuan = 50000
                        }
                        if (stasiun[position] == "Gambir - Jakarta") {
                            hargaStasiunTujuan = 60000
                        }
                        if (stasiun[position] == "Sentolo - Yogyakarta") {
                            hargaStasiunTujuan = 70000
                        }
                        stasiunTujuan = stasiun[position]
                        bundle.putString("stasiunTujuan", stasiunTujuan)
                        hargaTotal = hargaStasiunAwal + hargaStasiunTujuan + hargaTiket + hargaPaket
                        txtPrice.text = "Rp. " + hargaTotal.toString()
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }

                }

            spinnerKelasKereta.onItemSelectedListener =
                object  : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                        if (kelasKereta[position] == "Ekonomi") {
                            hargaTiket = 50000
                        }
                        if (kelasKereta[position] == "Bisnis") {
                            hargaTiket = 75000
                        }
                        if (kelasKereta[position] == "Eksekutif") {
                            hargaTiket = 100000
                        }
                        if (kelasKereta[position] == "Premium") {
                            hargaTiket = 125000
                        }
                        bundle.putString("kelas", kelasKereta[position])
                        hargaTotal = hargaStasiunAwal + hargaTiket + hargaPaket
                        txtPrice.text = "Rp. " + hargaTotal.toString()
                    }
                    override fun onNothingSelected(parent: AdapterView<*>) {
                        TODO("Not yet implemented")
                    }
                }

            btnTotalHarga.setOnClickListener {
                if (stasiunAwal == stasiunTujuan) {
                    Toast.makeText(this@PlanInputActivity, "Stasiun awal dan stsiun tujuan tidak boleh sama.", Toast.LENGTH_SHORT).show()
                } else {
                    if (selectedDate == "") {
                        Toast.makeText(this@PlanInputActivity, "Harap mengisi tanggal keberangkatan.", Toast.LENGTH_SHORT).show()
                    } else {
                        bundle.putStringArray("paket", paket)
                        val intentToDashboardActivity = Intent(this@PlanInputActivity, DashboardActivity::class.java)
                        intentToDashboardActivity.putExtras(bundle)
                        startActivity(intentToDashboardActivity)
                        finish()
                    }
                }
            }
        }
    }

    private fun showDatePicker() {
        val datePicker = DatePickerDialog(this, { _, year, month, dayOfMonth ->
            selectedDate = "$dayOfMonth/${month + 1}/$year"
            bundle.putString("selectedDate", selectedDate)
            binding.btnTanggalKeberangkatan.text = selectedDate
        }, 2023, 9, 14)
        datePicker.show()
    }
}