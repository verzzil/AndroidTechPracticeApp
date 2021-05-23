package ru.itis.androidtechpracticeapp.data.models

import ru.itis.androidtechpracticeapp.data.db.models.UserDb

data class UserData(
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
        fun from(user: UserDb): UserData =
            UserData(
                user.id,
                user.birthday,
                user.cash,
                user.email,
                user.firstName,
                user.lastName,
                user.role,
                user.selfCoefficient
            )

        fun fromList(users: List<UserDb>): List<UserData> =
            users.map(::from)
    }

}