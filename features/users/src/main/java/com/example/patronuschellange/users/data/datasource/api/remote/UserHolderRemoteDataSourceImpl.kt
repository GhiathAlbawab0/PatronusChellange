package com.example.patronuschellange.users.data.datasource.api.remote

import com.example.patronuschellange.api.domain.Resource
import com.example.patronuschellange.users.data.datasource.api.service.PatronusApi
import com.example.patronuschellange.users.domain.mappers.UserDeviceHolderDetailsMapper
import com.example.patronuschellange.users.domain.mappers.UserDeviceHolderMapper
import com.example.patronuschellange.users.domain.model.UserDeviceHolder
import com.example.patronuschellange.users.domain.model.UserDeviceHolderDetails
import javax.inject.Inject
import retrofit2.HttpException

internal class UserHolderRemoteDataSourceImpl @Inject constructor(
    private val api: PatronusApi,
    private val userDeviceHolderMapper: UserDeviceHolderMapper,
    private val userDeviceHolderDetailsMapper: UserDeviceHolderDetailsMapper,
) : UserHolderRemoteDataSource {
    override suspend fun getDeviceHolderUsers(): Resource<List<UserDeviceHolder>> {
        return try {
            val result = api.getDeviceHolderUsers()
            if (result.isSuccessful)
                result.body()?.let {
                    Resource.Success(it.customers.map { customer ->
                        userDeviceHolderMapper.mapToModel(
                            customer
                        )
                    })
                } ?: Resource.Error<Nothing>("Empty Response.")
            else
                Resource.Error<Nothing>(result.message() ?: "Unknown Network Error.")
        } catch (e: Exception) {
            when (e) {
                is HttpException -> Resource.Error<Nothing>(e.message ?: "Unknown Network Error.")
                else -> Resource.Error<Nothing>(e.message ?: "Unknown Error.")
            }
        }
    }

    override suspend fun getUserDetails(id: Long): Resource<UserDeviceHolderDetails> {
        return try {
            val result = api.getUserDetails(id)
            if (result.isSuccessful)
                result.body()?.let {
                    Resource.Success(userDeviceHolderDetailsMapper.mapToModel(it))
                } ?: Resource.Error<Nothing>("Empty Response.")
            else
                Resource.Error<Nothing>(result.message() ?: "Unknown Network Error.")
        } catch (e: Exception) {
            when (e) {
                is HttpException -> Resource.Error<Nothing>(e.message ?: "Unknown Network Error.")
                else -> Resource.Error<Nothing>(e.message ?: "Unknown Error.")
            }
        }
    }
}