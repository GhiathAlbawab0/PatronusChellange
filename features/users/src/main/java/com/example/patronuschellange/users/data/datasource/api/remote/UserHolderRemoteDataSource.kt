package com.example.patronuschellange.users.data.datasource.api.remote

import com.example.patronuschellange.api.domain.Resource
import com.example.patronuschellange.users.domain.model.UserDeviceHolder
import com.example.patronuschellange.users.domain.model.UserDeviceHolderDetails

interface UserHolderRemoteDataSource {
    suspend fun getDeviceHolderUsers(): Resource<List<UserDeviceHolder>>
    suspend fun getUserDetails(id: Long): Resource<UserDeviceHolderDetails>
}