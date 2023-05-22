package com.example.patronuschellange.users.domain.usecase

import com.example.patronuschellange.api.domain.Resource
import com.example.patronuschellange.users.domain.repository.UserHolderRepository
import com.example.patronuschellange.users.presentaion.screen.userlist.model.UserUI
import com.example.patronuschellange.users.presentaion.screen.userlist.model.mappers.UserUiMapper
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetDeviceHolderUsersUseCase @Inject constructor(
    private val repository: UserHolderRepository,
    private val userUiMapper: UserUiMapper,
) {
    operator fun invoke(): Flow<Resource<List<UserUI>>> = flow {
        emit(Resource.Loading())
        when (val result = repository.getDeviceHolderUsers()) {
            is Resource.Error<*> -> emit(result)
            is Resource.Loading -> {}
            is Resource.Success -> emit(Resource.Success(result.data.map { userUiMapper.mapToView(it) }))
        }
    }
}