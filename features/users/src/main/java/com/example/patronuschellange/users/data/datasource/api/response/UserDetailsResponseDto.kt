package com.example.patronuschellange.users.data.datasource.api.response

import com.example.patronuschellange.users.data.datasource.api.model.AddressDto

data class UserDetailsResponseDto(
    val address: AddressDto,
    val currentLatitude: Double,
    val currentLongitude: Double,
    val firstName: String,
    val gender: String,
    val id: Long,
    val imageUrl: String,
    val lastName: String,
    val phoneNumber: String,
    val stickers: List<String>
)