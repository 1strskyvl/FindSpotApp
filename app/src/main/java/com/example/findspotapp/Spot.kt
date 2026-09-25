package com.example.findspotapp

data class Spot(
    val id: String,
    val name: String,
    val location: String,
    val atmosphere: String,
    val description: String,
    val pricePerPerson: Int,
    val rating: Double = 4.5,
    val category: String = "Cafe",
    val imageRes: Int
)