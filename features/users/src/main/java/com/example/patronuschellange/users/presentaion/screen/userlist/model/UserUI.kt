package com.example.patronuschellange.users.presentaion.screen.userlist.model

data class UserUI(
    val firstName: String,
    val lastName: String,
    val gender: Gender,
    val id: Long,
    val imageUrl: String?,
    val phoneNumber: String,
    val stickers: List<Sticker>
) {
    sealed class Gender(val value: String) {
        object Male : Gender("Male")
        object Female : Gender("Female")
        object Other : Gender("")

        companion object {
            fun newInstance(value: String): Gender {
                return when (value.lowercase()) {
                    "female" -> UserUI.Gender.Female
                    "male" -> UserUI.Gender.Male
                    else -> UserUI.Gender.Other
                }
            }
        }
    }

    sealed class Sticker(val value: String) {
        object Fam : Sticker("Fam")
        object Ban : Sticker("Ban")
        object Other : Sticker("")

        companion object {
            fun newInstance(value: String): Sticker {
                return when (value.lowercase()) {
                    "fam" -> UserUI.Sticker.Fam
                    "ban" -> UserUI.Sticker.Ban
                    else -> UserUI.Sticker.Other

                }
            }
        }
    }
}