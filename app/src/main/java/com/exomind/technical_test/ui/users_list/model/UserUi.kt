package com.exomind.technical_test.ui.users_list.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserUi(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val phone: String,
    val website: String
) : Parcelable