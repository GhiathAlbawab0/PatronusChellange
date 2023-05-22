package com.example.patronuschellange.users.presentaion.screen.userlist.model.mappers

import com.example.patronuschellange.common.presentation.model.mapper.UiMapper
import com.example.patronuschellange.users.domain.model.UserDeviceHolder
import com.example.patronuschellange.users.presentaion.screen.userlist.model.UserUI
import javax.inject.Inject

class UserUiMapper @Inject constructor() : UiMapper<UserDeviceHolder, UserUI> {
    override fun mapToView(input: UserDeviceHolder) = UserUI(
        firstName = input.firstName,
        lastName = input.lastName,
        gender = UserUI.Gender.newInstance(input.gender),
        id = input.id,
        imageUrl = input.imageUrl,
        phoneNumber = input.phoneNumber,
        stickers = input.stickers.map { UserUI.Sticker.newInstance(it) },
    )
}