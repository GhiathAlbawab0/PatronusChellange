package com.example.patronuschellange.users.domain.mappers

import com.example.patronuschellange.common.presentation.model.mapper.ModelMapper
import com.example.patronuschellange.users.data.datasource.api.response.UserDetailsResponseDto
import com.example.patronuschellange.users.domain.model.UserDeviceHolderDetails

class UserDeviceHolderDetailsMapper : ModelMapper<UserDetailsResponseDto, UserDeviceHolderDetails> {
    override fun mapToModel(input: UserDetailsResponseDto) = UserDeviceHolderDetails(
        address = "${input.address.street}, ${input.address.zip}\n ${input.address.city}",
        currentLatitude = input.currentLatitude,
        currentLongitude = input.currentLongitude,
        firstName = input.firstName,
        gender = input.gender,
        id = input.id,
        imageUrl = input.imageUrl,
        lastName = input.lastName,
        phoneNumber = input.phoneNumber,
        stickers = input.stickers,
    )
}