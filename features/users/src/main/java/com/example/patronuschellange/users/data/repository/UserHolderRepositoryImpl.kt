package com.example.patronuschellange.users.data.repository

import com.example.patronuschellange.users.data.datasource.api.remote.UserHolderRemoteDataSource
import com.example.patronuschellange.users.domain.repository.UserHolderRepository
import javax.inject.Inject

internal class UserHolderRepositoryImpl @Inject constructor(
    private val remoteDataSource: UserHolderRemoteDataSource
) : UserHolderRepository {
    override suspend fun getDeviceHolderUsers() = remoteDataSource.getDeviceHolderUsers()
    override suspend fun getUserDetails(id: Long) = remoteDataSource.getUserDetails(id)
}