package com.exomind.technical_test.ui.common

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract class BaseFragment(private val shouldShowBackButton: Boolean): Fragment() {

    override fun onResume() {
        super.onResume()

        (activity as? AppCompatActivity)?.apply {
            supportActionBar?.setDisplayHomeAsUpEnabled(shouldShowBackButton)
            supportActionBar?.setDisplayShowHomeEnabled(shouldShowBackButton)
        }
    }
}