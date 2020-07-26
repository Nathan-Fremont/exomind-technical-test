package com.exomind.technical_test.ui.common

import androidx.annotation.StringRes

data class BaseFragmentActionBarArgument(
    @StringRes val actionBarTitle: Int,
    val shouldShowActionBar: Boolean,
    val shouldShowBackButton: Boolean
)