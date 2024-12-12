package com.dicoding.instahelp.resident.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import android.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.dicoding.instahelp.R


class PengaturanFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate layout untuk fragment ini
        return inflater.inflate(R.layout.fragment_pengaturan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val profileCard: CardView = view.findViewById(R.id.card_profile)
        profileCard.setOnClickListener {
            // Pindah ke halaman detail profil
            Toast.makeText(requireContext(), "Profile clicked!", Toast.LENGTH_SHORT).show()
        }

        val profileSetting: LinearLayout = view.findViewById(R.id.section_settings)
        profileSetting.setOnClickListener {
            // Pindah ke pengaturan
            Toast.makeText(requireContext(), "Settings clicked!", Toast.LENGTH_SHORT).show()
        }
    }
}