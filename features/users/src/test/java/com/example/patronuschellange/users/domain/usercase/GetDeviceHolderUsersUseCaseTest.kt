package com.example.patronuschellange.users.domain.usercase

import com.example.patronuschellange.api.domain.Resource
import com.example.patronuschellange.users.data.repository.FakeUserHolderRepositoryImpl
import com.example.patronuschellange.users.domain.usecase.GetDeviceHolderUsersUseCase
import com.example.patronuschellange.users.presentaion.screen.userlist.model.mappers.UserUiMapper
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetDeviceHolderUsersUseCaseTest {
    private val repositoryImpl = FakeUserHolderRepositoryImpl()
    private lateinit var SUT: GetDeviceHolderUsersUseCase
    private val mapper = UserUiMapper()

    @Before
    fun setup() {
        SUT = GetDeviceHolderUsersUseCase(repositoryImpl, mapper)
    }

    @Test
    fun getUsers_FirstReturn_LoadingReturned() {
        runTest {
            val result = SUT().first()
            assertThat(result).isInstanceOf(Resource.Loading::class.java)
        }
    }

    @Test
    fun getUsers_LastReturn_SuccessReturned() {
        runTest {
            val result = SUT().last()
            assertThat(result).isInstanceOf(Resource.Success::class.java)
        }
    }

    @Test
    fun getUsers_Success_ListReturned() {
        runTest {
            val result = SUT().last()
            assertThat((result as Resource.Success).data).isEqualTo(
                repositoryImpl.dummyList.map { mapper.mapToView(it) }
            )
        }
    }

    @Test
    fun getUsers_Error_ErrorReturned() {
        repositoryImpl.setShouldReturnError(true)
        runTest {
            val result = SUT().last()
            assertThat(result).isInstanceOf(Resource.Error::class.java)
        }
    }
}
