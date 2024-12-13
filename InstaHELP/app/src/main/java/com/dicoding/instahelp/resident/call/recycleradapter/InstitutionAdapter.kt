package com.dicoding.instahelp.resident.call.recycleradapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.instahelp.API.Institutions
import com.dicoding.instahelp.R

class InstitutionAdapter(
    private val institutions: List<Institutions>,
    private val onItemClick: (Institutions) -> Unit
) : RecyclerView.Adapter<InstitutionAdapter.InstitutionViewHolder>() {

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
            // Nama institusi
            tvName.text = institution.name ?: "Nama tidak tersedia"

            // Alamat institusi
            tvLocation.text = institution.address ?: "Alamat tidak tersedia"

            // Ketersediaan
            tvAvailability.text = institution.availability ?: "Tidak tersedia"

            if (institution.availability == "Tersedia") {
                tvAvailability.setBackgroundResource(R.drawable.rounded_bg) // Background hijau (tersedia)
            } else {
                tvAvailability.setBackgroundResource(R.drawable.rounded_bg_red) // Background merah (tidak tersedia)
            }

            tvAvailability.setTextColor(itemView.context.getColor(R.color.white)) // Tetap putih

            // Jumlah kendaraan
            tvVehicleCount.text = institution.vehicleCount.toString()

            // Jarak (gunakan longitude atau kombinasi dengan latitude jika relevan)
            tvDistance.text = if (institution.longitude.isNotEmpty()) "${institution.longitude} km" else "Jarak tidak tersedia"

            // Set click listener untuk item
            itemView.setOnClickListener {
                onItemClick(institution)
            }
        }
    }
}
