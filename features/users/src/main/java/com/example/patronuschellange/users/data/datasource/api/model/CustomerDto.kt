package com.example.patronuschellange.users.data.datasource.api.model

data class CustomerDto(
    val firstName: String,
    val gender: String,
    val id: Long,
    val imageUrl: String?,
    val lastName: String,
    val phoneNumber: String,
    val stickers: List<String>
)
