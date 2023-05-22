package com.example.patronuschellange.users.domain.usecase

import com.example.patronuschellange.api.domain.Resource
import com.example.patronuschellange.users.domain.repository.UserHolderRepository
import com.example.patronuschellange.users.presentaion.screen.userdetail.model.UserDetailsUI
import com.example.patronuschellange.users.presentaion.screen.userdetail.model.mapper.UserDetailsUiMapper
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetUserDetailsUseCase @Inject constructor(
    private val repository: UserHolderRepository,
    private val mapper: UserDetailsUiMapper,
) {
    operator fun invoke(id: Long): Flow<Resource<UserDetailsUI>> = flow {
        emit(Resource.Loading())
        when (val result = repository.getUserDetails(id)) {
            is Resource.Error<*> -> emit(result)
            is Resource.Loading -> {}
            is Resource.Success -> emit(Resource.Success(mapper.mapToView(result.data)))
        }
    }
}