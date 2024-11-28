package com.dicoding.instahelp.resident.call.amburecycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.instahelp.R

class AmbulanceAdapter(private val ambulanceList: List<Ambulance>) : RecyclerView.Adapter<AmbulanceAdapter.AmbulanceViewHolder>() {

    // ViewHolder untuk setiap item
    inner class AmbulanceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ambulanceName: TextView = itemView.findViewById(R.id.ambulance_name)
        val driverName: TextView = itemView.findViewById(R.id.driver_name)
        val plateNumber: TextView = itemView.findViewById(R.id.plate_number)
        val availability: TextView = itemView.findViewById(R.id.availability)
    }

    // Menghubungkan layout item dengan ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AmbulanceViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ambulance, parent, false)
        return AmbulanceViewHolder(itemView)
    }

    // Mengisi data ke dalam ViewHolder
    override fun onBindViewHolder(holder: AmbulanceViewHolder, position: Int) {
        val ambulance = ambulanceList[position]
        holder.ambulanceName.text = ambulance.name
        holder.driverName.text = "Sopir: ${ambulance.driverName}"
        holder.plateNumber.text = "No. Plat: ${ambulance.plateNumber}"
        holder.availability.text = if (ambulance.isAvailable) "Tersedia" else "Tidak Tersedia"
    }

    override fun getItemCount(): Int {
        return ambulanceList.size
    }
}
