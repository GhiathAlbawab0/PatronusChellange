package com.example.patronuschellange.users.data.datasource.api.response

import com.example.patronuschellange.users.data.datasource.api.model.CustomerDto

data class UsersHoldingDevicesResponseDto(
    val customers: List<CustomerDto>
)