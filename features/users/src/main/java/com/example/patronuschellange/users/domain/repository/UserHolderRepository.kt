package com.example.patronuschellange.users.domain.repository

import com.example.patronuschellange.api.domain.Resource
import com.example.patronuschellange.users.domain.model.UserDeviceHolder
import com.example.patronuschellange.users.domain.model.UserDeviceHolderDetails

interface UserHolderRepository {
    suspend fun getDeviceHolderUsers(): Resource<List<UserDeviceHolder>>
    suspend fun getUserDetails(id: Long): Resource<UserDeviceHolderDetails>
}