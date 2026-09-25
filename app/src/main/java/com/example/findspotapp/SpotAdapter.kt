package com.example.findspotapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SpotAdapter(
    private var spotList: List<Spot>,
    private val onItemClick: (Spot) -> Unit
) : RecyclerView.Adapter<SpotAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val ivSpot: ImageView = view.findViewById(R.id.ivSpot)

        val tvName: TextView = view.findViewById(R.id.tvItemName)
        val tvLocation: TextView = view.findViewById(R.id.tvItemLocation)
        val tvRating: TextView = view.findViewById(R.id.tvItemRating)
        val tvPrice: TextView = view.findViewById(R.id.tvItemPrice)
        val tvDescription: TextView = view.findViewById(R.id.tvItemDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_spot, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val spot = spotList[position]

        // Menampilkan foto
        holder.ivSpot.setImageResource(spot.imageRes)

        // Menampilkan data
        holder.tvName.text = "☕ ${spot.name}"
        holder.tvLocation.text = "📍 ${spot.location}"
        holder.tvRating.text = "⭐ ${spot.rating}"
        holder.tvPrice.text = "💰 Rp ${String.format("%,d", spot.pricePerPerson).replace(',', '.')}"
        holder.tvDescription.text = spot.description

        holder.itemView.setOnClickListener {
            onItemClick(spot)
        }
    }

    override fun getItemCount(): Int = spotList.size

    fun updateData(newList: List<Spot>) {
        spotList = newList
        notifyDataSetChanged()
    }
}