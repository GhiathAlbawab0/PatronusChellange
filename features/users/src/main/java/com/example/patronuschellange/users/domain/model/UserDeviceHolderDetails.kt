package com.example.patronuschellange.users.domain.model

data class UserDeviceHolderDetails(
    val address: String,
    val currentLatitude: Double,
    val currentLongitude: Double,
    val firstName: String,
    val gender: String,
    val id: Long,
    val imageUrl: String?,
    val lastName: String,
    val phoneNumber: String,
    val stickers: List<String>
)