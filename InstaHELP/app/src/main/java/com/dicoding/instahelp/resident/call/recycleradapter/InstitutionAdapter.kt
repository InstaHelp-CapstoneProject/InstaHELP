package com.dicoding.instahelp.resident.call.recycleradapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.instahelp.API.Institutions
import com.dicoding.instahelp.R

class InstitutionAdapter(private val institutions: List<Institutions>) : RecyclerView.Adapter<InstitutionAdapter.InstitutionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstitutionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_hospital, parent, false)
        return InstitutionViewHolder(view)
    }

    override fun onBindViewHolder(holder: InstitutionViewHolder, position: Int) {
        val institution = institutions[position]
        holder.bind(institution)
    }

    override fun getItemCount(): Int = institutions.size

    inner class InstitutionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvName: TextView = itemView.findViewById(R.id.hospital_name)
        private val tvLocation: TextView = itemView.findViewById(R.id.hospital_location)
        private val tvAvailability: TextView = itemView.findViewById(R.id.availability_badge)
        private val tvVehicleCount: TextView = itemView.findViewById(R.id.vehicle_count)
        private val tvDistance: TextView = itemView.findViewById(R.id.distance)

        fun bind(institution: Institutions) {
            // Set nama institusi
            tvName.text = institution.name ?: "Nama tidak tersedia"

            // Set alamat institusi
            tvLocation.text = institution.address ?: "Alamat tidak tersedia"

            // Set ketersediaan
            tvAvailability.text = institution.availability ?: "Tidak tersedia"
            tvAvailability.setBackgroundResource(
                if (institution.availability == "Tersedia") R.drawable.ic_ambulance
                else R.drawable.ic_ambulance
            )

            // Set jumlah kendaraan
            tvVehicleCount.text = institution.vehicleCount?.toString() ?: "0"

            // Set jarak dalam format yang lebih deskriptif
            val distanceText = institution.longitude?.let { "${it} km" } ?: "Jarak tidak tersedia"
            tvDistance.text = distanceText
        }
    }
}
