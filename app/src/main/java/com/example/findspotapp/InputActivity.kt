package com.example.findspotapp

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class InputActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        val etLokasi = findViewById<AutoCompleteTextView>(R.id.etLokasi)
        val lokasiList = listOf("All", "Jakarta Selatan", "Jakarta Barat", "Jakarta Pusat", "Jakarta Timur", "Jakarta Utara")
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, lokasiList)
        etLokasi.setAdapter(adapter)

        findViewById<Button>(R.id.btnCari).setOnClickListener {
            val budget = findViewById<TextInputEditText>(R.id.etBudget).text.toString().toIntOrNull() ?: 0
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("EXTRA_LOKASI", etLokasi.text.toString())
                putExtra("EXTRA_BUDGET", budget)
            }
            startActivity(intent)
        }
    }
}