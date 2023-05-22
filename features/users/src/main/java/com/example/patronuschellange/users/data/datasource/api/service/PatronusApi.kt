package com.example.patronuschellange.users.data.datasource.api.service

import com.example.patronuschellange.users.data.datasource.api.response.UserDetailsResponseDto
import com.example.patronuschellange.users.data.datasource.api.response.UsersHoldingDevicesResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PatronusApi {

    @GET("users")
    suspend fun getDeviceHolderUsers(): Response<UsersHoldingDevicesResponseDto>

    @GET("users/{id}")
    suspend fun getUserDetails(
        @Path("id") id: Long
    ): Response<UserDetailsResponseDto>
}