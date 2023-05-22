package com.example.patronuschellange.users.presentaion.screen.userdetail.model.mapper

import com.example.patronuschellange.common.presentation.model.mapper.UiMapper
import com.example.patronuschellange.users.domain.model.UserDeviceHolderDetails
import com.example.patronuschellange.users.presentaion.screen.userdetail.model.UserDetailsUI
import com.example.patronuschellange.users.presentaion.screen.userlist.model.UserUI
import javax.inject.Inject

class UserDetailsUiMapper @Inject constructor() : UiMapper<UserDeviceHolderDetails, UserDetailsUI> {
    override fun mapToView(input: UserDeviceHolderDetails) = UserDetailsUI(
        firstName = input.firstName,
        lastName = input.lastName,
        gender = UserUI.Gender.newInstance(input.gender),
        id = input.id,
        imageUrl = input.imageUrl,
        phoneNumber = input.phoneNumber,
        stickers = input.stickers.map { UserUI.Sticker.newInstance(it) },
        currentLatitude = input.currentLatitude,
        currentLongitude = input.currentLongitude,
        address = input.address,
    )
}