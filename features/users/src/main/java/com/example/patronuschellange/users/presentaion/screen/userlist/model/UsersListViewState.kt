package com.example.patronuschellange.users.presentaion.screen.userlist.model

import androidx.compose.runtime.Immutable


@Immutable
data class UsersListViewState(
    val users: List<UserUI> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String = "",
) {
    companion object {
        val Empty = UsersListViewState()
    }
}