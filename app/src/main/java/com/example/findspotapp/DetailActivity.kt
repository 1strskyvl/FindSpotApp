package com.example.findspotapp

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.chip.Chip
import java.text.NumberFormat
import java.util.Locale

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Tombol Back
        findViewById<TextView>(R.id.btnBackDetail).setOnClickListener {
            finish()
        }

        // Ambil data dari Intent
        val name = intent.getStringExtra("SPOT_NAME") ?: ""
        val desc = intent.getStringExtra("SPOT_DESC") ?: ""
        val location = intent.getStringExtra("SPOT_LOC") ?: ""
        val price = intent.getIntExtra("SPOT_PRICE", 0)
        val image = intent.getIntExtra("SPOT_IMAGE", R.drawable.logoapp)
        val rating = intent.getDoubleExtra("SPOT_RATING", 0.0)
        val category = intent.getStringExtra("SPOT_CATEGORY") ?: "Cafe"
        val atmosphere = intent.getStringExtra("SPOT_ATMOSPHERE") ?: "-"

        // Hubungkan View
        val ivImage = findViewById<ImageView>(R.id.ivDetailImage)
        val tvName = findViewById<TextView>(R.id.tvDetailName)
        val tvRating = findViewById<TextView>(R.id.tvDetailRating)
        val tvCategory = findViewById<TextView>(R.id.tvCategory)
        val tvPrice = findViewById<TextView>(R.id.tvDetailPrice)
        val tvLocation = findViewById<TextView>(R.id.tvDetailLocation)
        val tvAbout = findViewById<TextView>(R.id.tvDetailAbout)
        val chipAtmosphere = findViewById<Chip>(R.id.chipAtmosphere)
        val btnReview = findViewById<Button>(R.id.btnAddReview)

        // Format Rupiah
        val rupiah = NumberFormat.getNumberInstance(Locale("in", "ID"))
        val harga = "Rp ${rupiah.format(price)} / orang"

        // Isi Data
        ivImage.setImageResource(image)
        tvName.text = name
        tvRating.text = "⭐ $rating"
        tvCategory.text = category
        tvPrice.text = harga
        tvLocation.text = "📍 $location"
        tvAbout.text = desc
        chipAtmosphere.text = atmosphere

        // Dummy Add Review
        btnReview.setOnClickListener {
            Toast.makeText(
                this,
                "Fitur Add Review akan segera hadir 😊",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}