package com.brightskiesinc.mangm.presentation.main

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.brightskiesinc.mangm.presentation.base.BaseFragment
import com.brightskiesinc.mangm.presentation.homescreen.GraphFragment
import com.brightskiesinc.mangm.presentation.homescreen.HistoryFragment
import com.brightskiesinc.mangm.presentation.homescreen.ViewPagerAdapter
import com.brightskiesinc.mangmapp.R
import com.brightskiesinc.mangmapp.databinding.FragmentMangmMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.util.Locale


class MainFragment : BaseFragment<FragmentMangmMainBinding>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = getView(FragmentMangmMainBinding.inflate(inflater, container, false))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.i("TAG", "onViewCreated:testtttt1 ${Resources.getSystem().getConfiguration().locale.getLanguage()}")
        Log.i("TAG", "onViewCreated:testtttt2 ${Locale.getDefault().language}")

        setupViewPager()
    }

    private fun setupViewPager() {

        val viewPager: ViewPager2 = binding.viewPager

        val graphFragment = GraphFragment()
        val historyGraph = HistoryFragment()
        val tabTitles = arrayOf(
            context?.getString(R.string.graph_tab),
            context?.getString(R.string.history_tab)
        )

        val adapter = ViewPagerAdapter(childFragmentManager, lifecycle, tabTitles)
        adapter.addFragment(graphFragment)
        adapter.addFragment(historyGraph)

        viewPager.adapter = adapter
        viewPager.isUserInputEnabled = false     //to disable swipe behavior
        val tabLayout: TabLayout = binding.tabLayout
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = (viewPager.adapter as ViewPagerAdapter).getTabTitle(position)
        }.attach()

    }
}