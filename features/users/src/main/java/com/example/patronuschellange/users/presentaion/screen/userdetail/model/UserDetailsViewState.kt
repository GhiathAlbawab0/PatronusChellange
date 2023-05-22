package com.example.patronuschellange.users.presentaion.screen.userdetail.model

import androidx.compose.runtime.Immutable

@Immutable
data class UserDetailsViewState(
    val userDetails: UserDetailsUI = UserDetailsUI.Empty,
    val isLoading: Boolean = false,
    val errorMessage: String = "",
) {
    companion object {
        val Empty = UserDetailsViewState()
    }
}