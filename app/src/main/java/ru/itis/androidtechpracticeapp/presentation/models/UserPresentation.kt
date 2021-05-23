package ru.itis.androidtechpracticeapp.presentation.models

import ru.itis.androidtechpracticeapp.data.models.UserData
import ru.itis.androidtechpracticeapp.domain.models.UserDomain
import java.util.*

data class UserPresentation(
    var id: Int = 0,
    var birthday: Long,
    var cash: Int,
    var email: String,
    var firstName: String,
    var lastName: String,
    var role: String,
    var selfCoefficient: Double,
) {

    companion object {
        var users = listOf(
            UserPresentation(
                1,
                1,
                20000,
                "xannanov.albert@mail.ru",
                "Albert",
                "Khannanov",
                "Admin",
                1.0,
            ),
            UserPresentation(
                2,
                2,
                1500,
                "timur.batrshin@mail.ru",
                "Timur",
                "Batrshin",
                "User",
                1.0,
            ),
        )

        val testUser = users[0]

        fun from(user: UserDomain): UserPresentation =
            UserPresentation(
                user.id,
                user.birthday,
                user.cash,
                user.email,
                user.firstName,
                user.lastName,
                user.role,
                user.selfCoefficient
            )

        fun fromList(users: List<UserDomain>): List<UserPresentation> =
            users.map(::from)
    }

    fun getFullName(): String =
        "$firstName $lastName"
}