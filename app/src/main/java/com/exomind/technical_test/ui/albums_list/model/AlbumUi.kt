package com.exomind.technical_test.ui.albums_list.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AlbumUi(
    val id: Int,
    val title: String
) : Parcelable