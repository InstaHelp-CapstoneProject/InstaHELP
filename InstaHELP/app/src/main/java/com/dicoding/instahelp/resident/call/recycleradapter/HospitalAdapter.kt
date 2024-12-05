package com.dicoding.instahelp.resident.call.recycleradapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.instahelp.R
import android.widget.Button
import android.widget.ImageView

class HospitalAdapter(private val hospitalList: List<HospitalItem>, private val onCallClick: (HospitalItem) -> Unit) :
    RecyclerView.Adapter<HospitalAdapter.HospitalViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HospitalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_hospital, parent, false)
        return HospitalViewHolder(view)
    }

    override fun onBindViewHolder(holder: HospitalViewHolder, position: Int) {
        val hospital = hospitalList[position]
        holder.bind(hospital)
    }

    override fun getItemCount(): Int = hospitalList.size

    inner class HospitalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val hospitalImage: ImageView = itemView.findViewById(R.id.hospital_image)
        private val hospitalName: TextView = itemView.findViewById(R.id.hospital_name)
        private val hospitalLocation: TextView = itemView.findViewById(R.id.hospital_location)
        private val availabilityBadge: TextView = itemView.findViewById(R.id.availability_badge)
        private val vehicleCount: TextView = itemView.findViewById(R.id.vehicle_count)
        private val locationIcon: ImageView = itemView.findViewById(R.id.location_icon)
        private val distance: TextView = itemView.findViewById(R.id.distance)
        private val callButton: Button = itemView.findViewById(R.id.call_button)

        fun bind(hospital: HospitalItem) {
            // Set Image, Name, Location, etc.
            hospitalImage.setImageResource(R.drawable.placeholder_image) // Placeholder image
            hospitalName.text = hospital.name
            hospitalLocation.text = hospital.location
            availabilityBadge.text = hospital.availability
            vehicleCount.text = hospital.vehicleCount.toString()
            distance.text = hospital.distance
            // Icon kendaraan
            vehicleCount.setCompoundDrawablesWithIntrinsicBounds(hospital.vehicleIcon, 0, 0, 0)
            // Badge verifikasi
            if (hospital.isVerified) {
                availabilityBadge.setBackgroundResource(R.drawable.rounded_bg)
            } else {
                availabilityBadge.setBackgroundResource(R.drawable.rounded_bg_unverified)
            }

            // Button Panggil click listener
            callButton.setOnClickListener {
                onCallClick(hospital)
            }
        }
    }
}