package com.example.patronuschellange.users.presentaion.screen.userdetail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.patronuschellange.api.domain.Resource
import com.example.patronuschellange.users.domain.usecase.GetUserDetailsUseCase
import com.example.patronuschellange.users.presentaion.screen.userdetail.model.UserDetailsViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class UserDetailsViewmodel @Inject constructor(
    private val userDetailsUseCase: GetUserDetailsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(UserDetailsViewState.Empty)
    val state = _state.asStateFlow()

    val showError = mutableStateOf(false)

    fun getUserDetails(id: Long) {
        viewModelScope.launch {
            userDetailsUseCase.invoke(id).collect {
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
                            userDetails = it.data,
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