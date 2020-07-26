package com.exomind.technical_test.repository.api.model

data class PhotoApi(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)