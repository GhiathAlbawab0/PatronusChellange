package com.example.patronuschellange.users.presentaion.screen.userlist

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.patronuschellange.api.domain.Resource
import com.example.patronuschellange.users.domain.usecase.GetDeviceHolderUsersUseCase
import com.example.patronuschellange.users.presentaion.screen.userlist.model.UsersListViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class UsersHolderViewmodel @Inject constructor(
    private val usersUseCase: GetDeviceHolderUsersUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(UsersListViewState.Empty)
    val state = _state.asStateFlow()

    val showError = mutableStateOf(false)

    init {
        getDeviceHolderUsers()
    }

    fun getDeviceHolderUsers() {
        viewModelScope.launch {
            usersUseCase().collect {
                when (it) {
                    is Resource.Error<*> -> {
                        showError.value = true
                        _state.update { oldState ->
                            oldState.copy(
                                isLoading = false,
                                errorMessage = it.message ?: "Unknown Error"
                            )
                        }
                    }

                    is Resource.Loading -> _state.update { oldState ->
                        oldState.copy(
                            isLoading = true,
                            errorMessage = ""
                        )
                    }

                    is Resource.Success -> _state.update { oldState ->
                        oldState.copy(
                            users = it.data,
                            isLoading = false,
                            errorMessage = ""
                        )
                    }
                }
            }
        }
    }

    fun closeErrorDialog() {
        showError.value = false
    }
}