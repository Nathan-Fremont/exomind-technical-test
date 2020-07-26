package com.exomind.technical_test.repository.api.model

data class UserApi(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val phone: String,
    val website: String
)