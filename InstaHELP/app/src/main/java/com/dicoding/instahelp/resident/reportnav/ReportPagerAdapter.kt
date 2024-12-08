package com.dicoding.instahelp.resident.reportnav

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ReportPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 4 // Jumlah tab
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ReportedFragment() // Ganti dengan fragment yang sesuai
            1 -> ProcessedFragment() // Ganti dengan fragment yang sesuai
            2 -> CompletedFragment() // Ganti dengan fragment yang sesuai
            3 -> RejectedFragment() // Ganti dengan fragment yang sesuai
            else -> throw IllegalStateException("Unexpected position $position")
        }
    }
}
