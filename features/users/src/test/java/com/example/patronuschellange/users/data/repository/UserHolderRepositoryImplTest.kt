@file:OptIn(
    ExperimentalCoroutinesApi::class, ExperimentalCoroutinesApi::class,
)

package com.example.patronuschellange.users.data.repository

import com.example.patronuschellange.api.domain.Resource
import com.example.patronuschellange.users.data.datasource.api.remote.UserHolderRemoteDataSource
import com.example.patronuschellange.users.domain.model.UserDeviceHolder
import com.example.patronuschellange.users.domain.model.UserDeviceHolderDetails
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UserHolderRepositoryImplTest {
    private val remoteDataSourceMock: UserHolderRemoteDataSource = mockk()
    private lateinit var SUT: UserHolderRepositoryImpl

    @Before
    fun setup() {
        SUT = UserHolderRepositoryImpl(
            remoteDataSourceMock,
        )
    }

    //getDeviceHolderUsers Testing

    @Test
    fun getDeviceHolderUsers_RemoteLoading_LoadingReturned() {
        runTest {
            getDeviceHolderUsers_remoteDataSourceLoading()
            val result = SUT.getDeviceHolderUsers()
            assertThat(result).isInstanceOf(Resource.Loading::class.java)
        }
    }

    @Test
    fun getDeviceHolderUsers_RemoteSuccess_SuccessReturned() {
        runTest {
            getDeviceHolderUsers_remoteDataSourceSuccess()
            val result = SUT.getDeviceHolderUsers()
            assertThat(result).isInstanceOf(Resource.Success::class.java)
        }
    }

    @Test
    fun getDeviceHolderUsers_RemoteSuccess_SuccessReturnedWithData() {
        runTest {
            getDeviceHolderUsers_remoteDataSourceSuccess()
            val result = SUT.getDeviceHolderUsers()
            assertThat((result as Resource.Success).data).isEqualTo(getDummyUserDeviceHolder())
        }
    }

    @Test
    fun getDeviceHolderUsers_RemoteSuccess_SuccessReturnedWithoutData() {
        runTest {
            getDeviceHolderUsers_remoteDataSourceSuccessWithEmptyData()
            val result = SUT.getDeviceHolderUsers()
            assertThat((result as Resource.Success).data).isEqualTo(emptyList<UserDeviceHolder>())
        }
    }

    @Test
    fun getDeviceHolderUsers_RemoteFail_ErrorReturned() {
        runTest {
            getDeviceHolderUsers_remoteDataSourceFail()
            val result = SUT.getDeviceHolderUsers()
            assertThat(result).isInstanceOf(Resource.Error::class.java)
        }
    }

    private fun getDeviceHolderUsers_remoteDataSourceLoading() {
        coEvery {
            remoteDataSourceMock.getDeviceHolderUsers()
        } returns Resource.Loading()
    }

    private fun getDeviceHolderUsers_remoteDataSourceSuccess() {
        coEvery {
            remoteDataSourceMock.getDeviceHolderUsers()
        } returns Resource.Success(
            getDummyUserDeviceHolder()
        )
    }

    private fun getDeviceHolderUsers_remoteDataSourceSuccessWithEmptyData() {
        coEvery {
            remoteDataSourceMock.getDeviceHolderUsers()
        } returns Resource.Success(
            listOf()
        )
    }

    private fun getDeviceHolderUsers_remoteDataSourceFail() {
        coEvery { remoteDataSourceMock.getDeviceHolderUsers() } returns Resource.Error<Nothing>(
            "Error"
        )
    }

    // getUserDetails Testing

    @Test
    fun getUserDetails_RemoteLoading_LoadingReturned() {
        runTest {
            getUserDetails_remoteDataSourceLoading()
            val result = SUT.getUserDetails(1)
            assertThat(result).isInstanceOf(Resource.Loading::class.java)
        }
    }

    @Test
    fun getUserDetails_RemoteSuccess_SuccessReturned() {
        runTest {
            getUserDetails_remoteDataSourceSuccess()
            val result = SUT.getUserDetails(1)
            assertThat(result).isInstanceOf(Resource.Success::class.java)
        }
    }

    @Test
    fun getUserDetails_RemoteSuccess_SuccessReturnedWithData() {
        runTest {
            getUserDetails_remoteDataSourceSuccess()
            val result = SUT.getUserDetails(1)
            assertThat((result as Resource.Success).data).isEqualTo(getDummyUserDeviceHolderDetails())
        }
    }

    @Test
    fun getUserDetails_RemoteFail_ErrorReturned() {
        runTest {
            getUserDetails_remoteDataSourceFail()
            val result = SUT.getUserDetails(1)
            assertThat(result).isInstanceOf(Resource.Error::class.java)
        }
    }

    private fun getUserDetails_remoteDataSourceLoading() {
        coEvery {
            remoteDataSourceMock.getUserDetails(1)
        } returns Resource.Loading()
    }

    private fun getUserDetails_remoteDataSourceSuccess() {
        coEvery {
            remoteDataSourceMock.getUserDetails(1)
        } returns Resource.Success(
            getDummyUserDeviceHolderDetails()
        )
    }

    private fun getUserDetails_remoteDataSourceFail() {
        coEvery { remoteDataSourceMock.getUserDetails(1) } returns Resource.Error<Nothing>(
            "Error"
        )
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