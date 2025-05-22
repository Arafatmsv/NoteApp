package com.ara.lesson_3.ui.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ara.lesson_3.ui.fragments.onboard.PagesFragment
import com.ara.lesson_3.ui.fragments.onboard.PagesFragment.Companion.ARG_ONBOARD_POSITION

class PagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {

    override fun createFragment(position: Int) = PagesFragment().apply() {
        arguments = Bundle().apply {
            putInt(ARG_ONBOARD_POSITION, position)
        }
    }

    override fun getItemCount(): Int = 3
}