@file:OptIn(
    ExperimentalCoroutinesApi::class, ExperimentalCoroutinesApi::class,
)

package com.example.patronuschellange.users.data.datasource.api.remote

import com.example.patronuschellange.api.domain.Resource
import com.example.patronuschellange.users.data.datasource.api.response.UserDetailsResponseDto
import com.example.patronuschellange.users.data.datasource.api.response.UsersHoldingDevicesResponseDto
import com.example.patronuschellange.users.data.datasource.api.service.PatronusApi
import com.example.patronuschellange.users.domain.mappers.UserDeviceHolderDetailsMapper
import com.example.patronuschellange.users.domain.mappers.UserDeviceHolderMapper
import com.google.common.truth.Truth
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.mockk.coEvery
import io.mockk.mockk
import java.lang.reflect.Type
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class UserHolderRemoteDataSourceImplTest {

    private lateinit var SUT: UserHolderRemoteDataSourceImpl
    private val api: PatronusApi = mockk()
    private val userDeviceHolderMapper = UserDeviceHolderMapper()
    private val userDeviceHolderDetailsMapper = UserDeviceHolderDetailsMapper()

    @Before
    fun setup() {
        SUT = UserHolderRemoteDataSourceImpl(
            api,
            userDeviceHolderMapper,
            userDeviceHolderDetailsMapper
        )
    }

    // getDeviceHolderUsers Testing

    @Test
    fun getDeviceHolderUsers_apiReturnData_DataReturned() {
        runTest {
            getDeviceHolderUsers_returnSuccessWithData()
            val result = SUT.getDeviceHolderUsers()
            Truth.assertThat((result as Resource.Success).data).hasSize(2)
        }
    }

    @Test
    fun getDeviceHolderUsers_apiReturnData_SuccessReturned() {
        runTest {
            getDeviceHolderUsers_returnSuccessWithData()
            val result = SUT.getDeviceHolderUsers()
            Truth.assertThat(result).isInstanceOf(Resource.Success::class.java)
        }
    }

    @Test
    fun getDeviceHolderUsers_apiReturnEmpty_SuccessReturned() {
        runTest {
            getDeviceHolderUsers_returnSuccessWithoutData()
            val result = SUT.getDeviceHolderUsers()
            Truth.assertThat((result as Resource.Success).data).isEmpty()
        }
    }

    @Test
    fun getDeviceHolderUsers_apiReturnError_ErrorReturned() {
        runTest {
            getDeviceHolderUsers_returnError()
            val result = SUT.getDeviceHolderUsers()
            Truth.assertThat(result).isInstanceOf(Resource.Error::class.java)
        }
    }

    @Test
    fun getDeviceHolderUsers_apiThrowsException_ErrorReturned() {
        runTest {
            getDeviceHolderUsers_returnException()
            val result = SUT.getDeviceHolderUsers()
            Truth.assertThat(result).isInstanceOf(Resource.Error::class.java)
        }
    }

    private fun getDeviceHolderUsers_returnSuccessWithData() {
        coEvery { api.getDeviceHolderUsers() } returns Response.success(getDummyUsers())
    }

    private fun getDeviceHolderUsers_returnSuccessWithoutData() {
        coEvery { api.getDeviceHolderUsers() } returns Response.success(getDummyEmptyProperties())
    }

    private fun getDeviceHolderUsers_returnException() {
        coEvery { api.getDeviceHolderUsers() } throws Exception("testException")
    }

    private fun getDeviceHolderUsers_returnError() {
        coEvery { api.getDeviceHolderUsers() } returns Response.error(
            403,
            "{\"key\":\"someStuff\"}"
                .toResponseBody("application/json".toMediaTypeOrNull())
        )
    }


    // getUserDetails Testing
    @Test
    fun getUserDetails_apiReturnData_DataReturned() {
        runTest {
            getUserDetails_returnSuccessWithData()
            val result = SUT.getUserDetails(1)
            Truth.assertThat((result as Resource.Success).data).isNotNull()
        }
    }

    @Test
    fun getUserDetails_apiReturnData_SuccessReturned() {
        runTest {
            getUserDetails_returnSuccessWithData()
            val result = SUT.getUserDetails(1)
            Truth.assertThat(result).isInstanceOf(Resource.Success::class.java)
        }
    }

    @Test
    fun getUserDetails_apiReturnError_ErrorReturned() {
        runTest {
            getUserDetails_returnError()
            val result = SUT.getUserDetails(1)
            Truth.assertThat(result).isInstanceOf(Resource.Error::class.java)
        }
    }

    @Test
    fun getUserDetails_apiThrowsException_ErrorReturned() {
        runTest {
            getUserDetails_returnException()
            val result = SUT.getUserDetails(1)
            Truth.assertThat(result).isInstanceOf(Resource.Error::class.java)
        }
    }

    private fun getUserDetails_returnSuccessWithData() {
        coEvery { api.getUserDetails(1) } returns Response.success(getDummyUserDetails())
    }

    private fun getUserDetails_returnException() {
        coEvery { api.getUserDetails(1) } throws Exception("testException")
    }

    private fun getUserDetails_returnError() {
        coEvery { api.getUserDetails(1) } returns Response.error(
            403,
            "{\"key\":\"someStuff\"}"
                .toResponseBody("application/json".toMediaTypeOrNull())
        )
    }


    private fun getDummyUserDetails(): UserDetailsResponseDto {
        val testItems =
            "{\n" +
                    "    \"id\": 20,\n" +
                    "    \"imageUrl\": null,\n" +
                    "    \"currentLatitude\": 37.7749,\n" +
                    "    \"currentLongitude\": -122.4194,\n" +
                    "    \"firstName\": \"John\",\n" +
                    "    \"lastName\": \"Michael\",\n" +
                    "    \"stickers\": [\n" +
                    "        \"Ban\"\n" +
                    "    ],\n" +
                    "    \"gender\": \"female\",\n" +
                    "    \"phoneNumber\": \"123-313-6665\",\n" +
                    "    \"address\": {\n" +
                    "        \"street\": \"123 Main St\",\n" +
                    "        \"city\": \"San Francisco\",\n" +
                    "        \"state\": \"CA\",\n" +
                    "        \"zip\": \"94111\",\n" +
                    "        \"country\": \"USA\"\n" +
                    "    }\n" +
                    "}"
        val imageDtoType: Type = object : TypeToken<UserDetailsResponseDto>() {}.type
        return Gson().fromJson(testItems, imageDtoType)
    }

    private fun getDummyEmptyProperties(): UsersHoldingDevicesResponseDto {
        val testItems =
            "{\n" +
                    "    \"customers\": [\n" +
                    "    ]\n" +
                    "}"
        val imageDtoType: Type = object : TypeToken<UsersHoldingDevicesResponseDto>() {}.type
        return Gson().fromJson(testItems, imageDtoType)
    }

    /**
     * creates dummy data from the api
     */
    private fun getDummyUsers(): UsersHoldingDevicesResponseDto {
        val testItems =
            "{\n" +
                    "    \"customers\": [\n" +
                    "        {\n" +
                    "            \"id\": 1,\n" +
                    "            \"firstName\": \"John\",\n" +
                    "            \"lastName\": \"Doe\",\n" +
                    "            \"gender\": \"MALE\",\n" +
                    "            \"phoneNumber\": \"123-456-7890\",\n" +
                    "            \"imageUrl\": \"https://fastly.picsum.photos/id/473/200/300.jpg?hmac=WYG6etF60iOJeGoFVY1hVDMakbBRS32ZDGNkVZhF6-8\",\n" +
                    "            \"stickers\": [\n" +
                    "                \"Fam\"\n" +
                    "            ]\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": 2,\n" +
                    "            \"firstName\": \"Jane\",\n" +
                    "            \"lastName\": \"Doe\",\n" +
                    "            \"gender\": \"FEMALE\",\n" +
                    "            \"phoneNumber\": \"123-456-7891\",\n" +
                    "            \"imageUrl\": \"https://fastly.picsum.photos/id/445/400/400.jpg?hmac=CCjqlZXQQ_5kl0X6naMjQKUWSbQloDYImyB9zGFOA8M\",\n" +
                    "            \"stickers\": [\n" +
                    "                \"Fam\",\n" +
                    "                \"Ban\"\n" +
                    "            ]\n" +
                    "        }\n" +
                    "    ]\n" +
                    "}"

        val dtoType: Type = object : TypeToken<UsersHoldingDevicesResponseDto>() {}.type
        return Gson().fromJson(testItems, dtoType)
    }
}