package com.ara.lesson_3.ui.fragments.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.ara.lesson_3.App
import com.ara.lesson_3.R
import com.ara.lesson_3.utils.PreferenceHelper

class SplashFragment : Fragment(R.layout.fragment_splash) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed({
            if (PreferenceHelper.isOnboardShow()) {
                findNavController().navigate(
                    R.id.action_splash_to_homeFragment, null,
                    NavOptions.Builder().setPopUpTo(R.id.splashFragment, true).build()
                )
            } else {
                findNavController().navigate(R.id.action_splash_to_onBoardFragment,
                    null, NavOptions.Builder().setPopUpTo(R.id.splashFragment, true).build())
            } }, 1500)
    }
}