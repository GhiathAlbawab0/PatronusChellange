package com.example.patronuschellange.users.presentaion.screen.userlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.patronuschellange.api.domain.Resource
import com.example.patronuschellange.users.domain.usecase.GetDeviceHolderUsersUseCase
import com.example.patronuschellange.users.presentaion.screen.userlist.model.UserUI
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class UsersHolderViewmodelTest {
    private lateinit var SUT: UsersHolderViewmodel
    private val getDeviceHolderUsersUseCase: GetDeviceHolderUsersUseCase = mockk()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @Test
    fun getDeviceHolderUsers_UseCaseLoading_LoadingReturned() {
        runTest {
            getDeviceHolderUsers_useCaseLoading()
            SUT = UsersHolderViewmodel(getDeviceHolderUsersUseCase)
            Truth.assertThat(SUT.state.value.isLoading).isTrue()
        }
    }

    @Test
    fun getDeviceHolderUsers_UseCaseSuccess_SuccessReturned() {
        runTest {
            getDeviceHolderUsers_useCaseSuccess()
            SUT = UsersHolderViewmodel(getDeviceHolderUsersUseCase)
            Truth.assertThat(SUT.state.value.isLoading).isFalse()
            Truth.assertThat(SUT.state.value.users).isEqualTo(getDummyUsersList())
        }
    }

    @Test
    fun getDeviceHolderUsers_UseCaseSuccess_SuccessReturnedWithoutData() {
        runTest {
            getDeviceHolderUsers_useCaseSuccessWithEmptyData()
            SUT = UsersHolderViewmodel(getDeviceHolderUsersUseCase)
            Truth.assertThat(SUT.state.value.users).isEmpty()
        }
    }

    @Test
    fun getDeviceHolderUsers_UseCaseFail_ErrorReturned() {
        runTest {
            getDeviceHolderUsers_useCaseSourceFail()
            SUT = UsersHolderViewmodel(getDeviceHolderUsersUseCase)
            Truth.assertThat(SUT.state.value.errorMessage).isNotEmpty()
        }
    }

    private fun getDeviceHolderUsers_useCaseLoading() {
        coEvery {
            getDeviceHolderUsersUseCase.invoke()
        } returns flow { emit(Resource.Loading()) }
    }

    private fun getDeviceHolderUsers_useCaseSuccess() {
        coEvery {
            getDeviceHolderUsersUseCase.invoke()
        } returns flow { emit(Resource.Success(getDummyUsersList())) }
    }


    private fun getDeviceHolderUsers_useCaseSuccessWithEmptyData() {
        coEvery {
            getDeviceHolderUsersUseCase.invoke()
        } returns flow { emit(Resource.Success(listOf())) }
    }


    private fun getDeviceHolderUsers_useCaseSourceFail() {
        coEvery {
            getDeviceHolderUsersUseCase.invoke()
        } returns flow {
            emit(Resource.Error<Nothing>("Error"))
        }
    }

    private fun getDummyUsersList() = listOf(
        UserUI(
            "Savannah",
            "Nguyen Nguyen Nguyen",
            UserUI.Gender.Female,
            1,
            "https://fastly.picsum.photos/id/473/200/300.jpg?hmac=WYG6etF60iOJeGoFVY1hVDMakbBRS32ZDGNkVZhF6-8",
            "123-456-7890 789 07 8 90",
            listOf(
                UserUI.Sticker.Fam,
                UserUI.Sticker.Ban,
                UserUI.Sticker.Ban,
            )
        ),
        UserUI(
            "Savannah",
            "Nguyen Nguyen Nguyen",
            UserUI.Gender.Female,
            2,
            "https://fastly.picsum.photos/id/473/200/300.jpg?hmac=WYG6etF60iOJeGoFVY1hVDMakbBRS32ZDGNkVZhF6-8",
            "123-456-7890 789 07 8 90",
            listOf(
                UserUI.Sticker.Fam,
                UserUI.Sticker.Ban,
                UserUI.Sticker.Ban,
            )
        ),
    )
}