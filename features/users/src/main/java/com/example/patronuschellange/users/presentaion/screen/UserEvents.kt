package com.example.patronuschellange.users.presentaion.screen

sealed class UserEvents {
    class GetDetails(val id: Long) : UserEvents()
    object OnDetailsBackPressed : UserEvents()
}
