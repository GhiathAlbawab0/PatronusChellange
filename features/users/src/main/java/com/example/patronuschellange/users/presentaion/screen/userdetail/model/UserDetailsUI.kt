package com.example.patronuschellange.users.presentaion.screen.userdetail.model

import com.example.patronuschellange.users.presentaion.screen.userlist.model.UserUI

data class UserDetailsUI(
    val firstName: String,
    val lastName: String,
    val gender: UserUI.Gender,
    val id: Long,
    val imageUrl: String?,
    val phoneNumber: String,
    val stickers: List<UserUI.Sticker>,
    val currentLatitude: Double,
    val currentLongitude: Double,
    val address: String,
) {
    companion object {
        val Empty = UserDetailsUI(
            "",
            "",
            UserUI.Gender.Other,
            Long.MIN_VALUE,
            "",
            "",
            emptyList(),
            Double.NaN,
            Double.NaN,
            "",
        )
    }
}