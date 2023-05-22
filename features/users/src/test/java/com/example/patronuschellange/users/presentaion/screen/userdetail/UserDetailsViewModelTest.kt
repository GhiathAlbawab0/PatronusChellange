package com.example.patronuschellange.users.presentaion.screen.userdetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.patronuschellange.api.domain.Resource
import com.example.patronuschellange.users.domain.usecase.GetUserDetailsUseCase
import com.example.patronuschellange.users.presentaion.screen.userdetail.model.UserDetailsUI
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
class UserDetailsViewModelTest {
    private lateinit var SUT: UserDetailsViewmodel
    private val useCase: GetUserDetailsUseCase = mockk()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        SUT = UserDetailsViewmodel(useCase)
    }

    @Test
    fun getUserDetails_UseCaseLoading_LoadingReturned() {
        runTest {
            getUserDetails_useCaseLoading()
            SUT.getUserDetails(1)
            Truth.assertThat(SUT.state.value.isLoading).isTrue()
        }
    }

    @Test
    fun getUserDetails_UseCaseSuccess_SuccessReturned() {
        runTest {
            getUserDetails_useCaseSuccess()
            SUT.getUserDetails(1)
            Truth.assertThat(SUT.state.value.isLoading).isFalse()
            Truth.assertThat(SUT.state.value.userDetails)
                .isEqualTo(getDummyUserDetails())
        }
    }

    @Test
    fun getUserDetails_UseCaseFail_ErrorReturned() {
        runTest {
            getUserDetails_useCaseSourceFail()
            SUT.getUserDetails(1)
            Truth.assertThat(SUT.state.value.errorMessage).isNotEmpty()
        }
    }

    private fun getUserDetails_useCaseLoading() {
        coEvery {
            useCase.invoke(1)
        } returns flow { emit(Resource.Loading()) }
    }

    private fun getUserDetails_useCaseSuccess() {
        coEvery {
            useCase.invoke(1)
        } returns flow { emit(Resource.Success(getDummyUserDetails())) }
    }

    private fun getUserDetails_useCaseSourceFail() {
        coEvery {
            useCase.invoke(1)
        } returns flow {
            emit(Resource.Error<Nothing>("Error"))
        }
    }

    private fun getDummyUserDetails() = UserDetailsUI(
        "Savannah",
        "Nguyen",
        UserUI.Gender.Female,
        1,
        "https://fastly.picsum.photos/id/473/200/300.jpg?hmac=WYG6etF60iOJeGoFVY1hVDMakbBRS32ZDGNkVZhF6-8",
        "123-456-7890 789 07 8 90",
        listOf(
            UserUI.Sticker.Fam,
            UserUI.Sticker.Ban,
            UserUI.Sticker.Ban,
        ),
        37.7749,
        -122.4194,
        "123 Main St, 94111 \n San Francisco"
    )
}