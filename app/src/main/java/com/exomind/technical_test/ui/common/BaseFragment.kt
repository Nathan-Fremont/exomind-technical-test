package com.exomind.technical_test.ui.common

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract class BaseFragment(private val baseFragmentActionBarArgument: BaseFragmentActionBarArgument): Fragment() {

    override fun onResume() {
        super.onResume()

        (activity as? AppCompatActivity)?.apply {
            if (baseFragmentActionBarArgument.shouldShowActionBar) {
                supportActionBar?.show()

                supportActionBar?.title = getString(baseFragmentActionBarArgument.actionBarTitle)

                supportActionBar?.setDisplayHomeAsUpEnabled(baseFragmentActionBarArgument.shouldShowBackButton)
                supportActionBar?.setDisplayShowHomeEnabled(baseFragmentActionBarArgument.shouldShowBackButton)
            } else {
                supportActionBar?.hide()
            }
        }
    }
}