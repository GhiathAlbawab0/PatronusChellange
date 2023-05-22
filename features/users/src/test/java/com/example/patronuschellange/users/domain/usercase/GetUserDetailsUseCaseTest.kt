package com.example.patronuschellange.users.domain.usercase

import com.example.patronuschellange.api.domain.Resource
import com.example.patronuschellange.users.data.repository.FakeUserHolderRepositoryImpl
import com.example.patronuschellange.users.domain.usecase.GetUserDetailsUseCase
import com.example.patronuschellange.users.presentaion.screen.userdetail.model.mapper.UserDetailsUiMapper
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetUserDetailsUseCaseTest {
    private val repositoryImpl = FakeUserHolderRepositoryImpl()
    private lateinit var SUT: GetUserDetailsUseCase
    private val mapper = UserDetailsUiMapper()

    @Before
    fun setup() {
        SUT = GetUserDetailsUseCase(repositoryImpl, mapper)
    }

    @Test
    fun getUserDetails_FirstReturn_LoadingReturned() {
        runTest {
            val result = SUT(1).first()
            assertThat(result).isInstanceOf(Resource.Loading::class.java)
        }
    }

    @Test
    fun getUserDetails_LastReturn_SuccessReturned() {
        runTest {
            val result = SUT(1).last()
            assertThat(result).isInstanceOf(Resource.Success::class.java)
        }
    }

    @Test
    fun getUserDetails_Success_ListReturned() {
        runTest {
            val result = SUT(1).last()
            assertThat((result as Resource.Success).data).isEqualTo(
                mapper.mapToView(repositoryImpl.dummyDetails)
            )
        }
    }

    @Test
    fun getUserDetails_Error_ErrorReturned() {
        repositoryImpl.setShouldReturnError(true)
        runTest {
            val result = SUT(1).last()
            assertThat(result).isInstanceOf(Resource.Error::class.java)
        }
    }
}
