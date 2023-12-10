package com.brightskiesinc.mangm.presentation.homescreen

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter


class ViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    tabTitles: Array<String?>
) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    private val fragmentList: ArrayList<Fragment> = ArrayList<Fragment>()
    private val tabTitles = tabTitles
    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

    fun addFragment(fragment: Fragment) {
        fragmentList.add(fragment)
    }

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    fun getTabTitle(position: Int): String? {
        return tabTitles[position]
    }

}