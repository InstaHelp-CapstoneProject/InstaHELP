package com.dicoding.instahelp.resident.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.dicoding.instahelp.R
import com.dicoding.instahelp.resident.reportnav.ReportPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import androidx.recyclerview.widget.RecyclerView

class LaporanFragment : Fragment() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var adapter: ReportPagerAdapter
    private lateinit var reportRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate layout untuk fragment ini
        val view = inflater.inflate(R.layout.fragment_laporan, container, false)

        // Inisialisasi TabLayout dan ViewPager
        tabLayout = view.findViewById(R.id.tabLayout)
        viewPager = view.findViewById(R.id.viewPager)

        // Setup ViewPager dengan Adapter
        adapter = ReportPagerAdapter(this) // Pastikan ReportPagerAdapter menerima Fragment
        viewPager.adapter = adapter

        // Hubungkan TabLayout dengan ViewPager
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Dilaporkan"
                1 -> tab.text = "Diproses"
                2 -> tab.text = "Selesai"
                3 -> tab.text = "Ditolak"
            }
        }.attach()

        return view
    }
}