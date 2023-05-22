package com.example.patronuschellange.users.domain.mappers

import com.example.patronuschellange.common.presentation.model.mapper.ModelMapper
import com.example.patronuschellange.users.data.datasource.api.model.CustomerDto
import com.example.patronuschellange.users.domain.model.UserDeviceHolder
import javax.inject.Inject

class UserDeviceHolderMapper : ModelMapper<CustomerDto, UserDeviceHolder> {
    override fun mapToModel(input: CustomerDto) = UserDeviceHolder(
        firstName = input.firstName,
        lastName = input.lastName,
        gender = input.gender,
        id = input.id,
        imageUrl = input.imageUrl,
        phoneNumber = input.phoneNumber,
        stickers = input.stickers,
    )
}