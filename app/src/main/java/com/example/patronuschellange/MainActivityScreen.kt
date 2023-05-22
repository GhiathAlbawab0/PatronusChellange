package com.example.patronuschellange

sealed class MainActivityScreen(val route: String) {
    object UsersListScreen : MainActivityScreen("UsersScreen")
    object UsersDetailsScreen : MainActivityScreen("UsersDetailsScreen")
}
