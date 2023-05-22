package com.example.patronuschellange.users.data.repository

import com.example.patronuschellange.api.domain.Resource
import com.example.patronuschellange.users.domain.model.UserDeviceHolder
import com.example.patronuschellange.users.domain.model.UserDeviceHolderDetails
import com.example.patronuschellange.users.domain.repository.UserHolderRepository

class FakeUserHolderRepositoryImpl : UserHolderRepository {

    private var shouldReturnError = false
    val dummyList = getDummyUserDeviceHolder()
    val dummyDetails = getDummyUserDeviceHolderDetails()


    override suspend fun getDeviceHolderUsers(): Resource<List<UserDeviceHolder>> {
        return if (shouldReturnError)
            Resource.Error<Nothing>("Error")
        else
            Resource.Success(dummyList)
    }

    override suspend fun getUserDetails(id: Long): Resource<UserDeviceHolderDetails> {
        return if (shouldReturnError)
            Resource.Error<Nothing>("Error")
        else
            Resource.Success(dummyDetails)
    }


    fun setShouldReturnError(value: Boolean) {
        shouldReturnError = value
    }

    private fun getDummyUserDeviceHolderDetails() =
        UserDeviceHolderDetails(
            "123 Main St, 94111\nSan Francisco",
            3.1,
            13.1,
            "TestFirstName",
            "male",
            1,
            null,
            "TestLastName",
            "123-313-6665",
            listOf("Fam"),
        )

    private fun getDummyUserDeviceHolder() = listOf(
        UserDeviceHolder(
            "TestFirstName",
            "male",
            1,
            null,
            "TestLastName",
            "123-313-6665",
            listOf("Fam"),
        )
    )

}