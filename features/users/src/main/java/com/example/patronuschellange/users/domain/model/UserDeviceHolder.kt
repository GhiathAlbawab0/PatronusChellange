package com.example.patronuschellange.users.domain.model

data class UserDeviceHolder(
    val firstName: String,
    val gender: String,
    val id: Long,
    val imageUrl: String?,
    val lastName: String,
    val phoneNumber: String,
    val stickers: List<String>
)