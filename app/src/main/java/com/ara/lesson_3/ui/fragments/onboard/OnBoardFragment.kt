package com.ara.lesson_3.ui.fragments.onboard

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.ara.lesson_3.App
import com.ara.lesson_3.R
import com.ara.lesson_3.databinding.FragmentOnBoardBinding
import com.ara.lesson_3.ui.adapters.PagerAdapter
import com.ara.lesson_3.utils.PreferenceHelper

class OnBoardFragment : Fragment() {
    private lateinit var binding: FragmentOnBoardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListeners()

        val adapter = PagerAdapter(this@OnBoardFragment)
        viewPager.adapter = adapter

        dotsIndicator.attachTo(viewPager)
    }

    private fun initialize() {
        binding.viewPager.adapter = PagerAdapter(this)
    }

    private fun setupListeners() = with(binding.viewPager) {
        registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) = with(binding)  {
                super.onPageSelected(position)
                if (position == 2) {
                    skip.visibility = View.INVISIBLE
                    btnStart.visibility = View.VISIBLE
                } else {
                    skip.visibility = View.VISIBLE
                    skip.setOnClickListener {
                        setCurrentItem(currentItem + 2, true)
                    }
                    btnStart.visibility = View.GONE
                }
            }
        })

        binding.btnStart.setOnClickListener {
            PreferenceHelper.setOnboardShow(true)
            findNavController().navigate(R.id.homeFragment, null, NavOptions.
            Builder().setPopUpTo(R.id.onBoardFragment, true).build())
        }
    }
}