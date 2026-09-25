package com.example.findspotapp

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.ChipGroup

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: SpotAdapter

    private val allSpots = listOf(

        Spot(
            "1",
            "Brew & Co.",
            "Jakarta Selatan",
            "Outdoor",
            "Kafe modern dengan kopi enak.",
            75000,
            4.5,
            "Cafe",
            R.drawable.brew_co
        ),

        Spot(
            "2",
            "Library Cafe",
            "Jakarta Selatan",
            "Quiet",
            "Cocok untuk nugas dan fokus.",
            30000,
            4.7,
            "Cafe",
            R.drawable.library_cafe
        ),

        Spot(
            "3",
            "The Rooftop",
            "Jakarta Selatan",
            "Outdoor",
            "Pemandangan gedung tinggi.",
            75000,
            4.8,
            "Cafe",
            R.drawable.the_rooftop
        ),

        Spot(
            "4",
            "Garden Hub",
            "Jakarta Barat",
            "Outdoor",
            "Taman yang asri.",
            40000,
            4.6,
            "Cafe",
            R.drawable.garden_hub
        ),

        Spot(
            "5",
            "Morning Coffee",
            "Jakarta Barat",
            "Quiet",
            "Sarapan tenang di pagi hari.",
            35000,
            4.4,
            "Cafe",
            R.drawable.morning_coffee
        ),

        Spot(
            "6",
            "City Center Work",
            "Jakarta Pusat",
            "Quiet",
            "Tempat kerja yang kondusif.",
            60000,
            4.6,
            "Cafe",
            R.drawable.city_center_work
        ),

        Spot(
            "7",
            "Park View",
            "Jakarta Pusat",
            "Outdoor",
            "Area taman luas.",
            45000,
            4.5,
            "Cafe",
            R.drawable.park_view
        ),

        Spot(
            "8",
            "Jazz Corner",
            "Jakarta Timur",
            "Quiet",
            "Menikmati musik jazz.",
            80000,
            4.9,
            "Cafe",
            R.drawable.jazz_corner
        ),

        Spot(
            "9",
            "Sport Arena",
            "Jakarta Utara",
            "Outdoor",
            "Area olahraga santai.",
            25000,
            4.3,
            "Cafe",
            R.drawable.sport_arena
        ),

        Spot(
            "10",
            "Old Town Cafe",
            "Jakarta Pusat",
            "Quiet",
            "Nuansa klasik yang nyaman.",
            55000,
            4.7,
            "Cafe",
            R.drawable.old_town_cafe
        )

    )

    private var currentLokasi = "All"
    private var currentBudget = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.btnBack).setOnClickListener {
            finish()
        }

        currentLokasi = intent.getStringExtra("EXTRA_LOKASI") ?: "All"
        currentBudget = intent.getIntExtra("EXTRA_BUDGET", 0)

        adapter = SpotAdapter(allSpots) { spot ->

            val intent = Intent(this, DetailActivity::class.java)

            intent.putExtra("SPOT_NAME", spot.name)
            intent.putExtra("SPOT_DESC", spot.description)
            intent.putExtra("SPOT_LOC", spot.location)
            intent.putExtra("SPOT_PRICE", spot.pricePerPerson)
            intent.putExtra("SPOT_IMAGE", spot.imageRes)
            intent.putExtra("SPOT_RATING", spot.rating)
            intent.putExtra("SPOT_CATEGORY", spot.category)
            intent.putExtra("SPOT_ATMOSPHERE", spot.atmosphere)

            startActivity(intent)
        }

        val rv = findViewById<RecyclerView>(R.id.rvSpots)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter

        applyFilters(null)

        findViewById<ChipGroup>(R.id.chipGroup)
            .setOnCheckedChangeListener { _, checkedId ->

                val suasana = when (checkedId) {
                    R.id.chipOutdoor -> "Outdoor"
                    R.id.chipQuiet -> "Quiet"
                    else -> null
                }

                applyFilters(suasana)
            }
    }

    private fun applyFilters(suasana: String?) {

        val filteredList = allSpots.filter { spot ->

            val matchLokasi =
                currentLokasi == "All" ||
                        spot.location.equals(currentLokasi, true)

            val matchHarga =
                currentBudget == 0 ||
                        spot.pricePerPerson <= currentBudget

            val matchSuasana =
                suasana == null ||
                        spot.atmosphere.equals(suasana, true)

            matchLokasi && matchHarga && matchSuasana
        }

        adapter.updateData(filteredList)
    }
}