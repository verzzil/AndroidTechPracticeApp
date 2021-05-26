package ru.itis.androidtechpracticeapp.data.models

import ru.itis.androidtechpracticeapp.data.api.responses.SocialLinksResponse
import ru.itis.androidtechpracticeapp.data.api.responses.UserResponse
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
    var socialLinks: List<SocialLinksResponse>
) {

    companion object {
        fun from(user: UserResponse): UserData =
            UserData(
                user.id,
                user.birthday,
                user.cash,
                user.email,
                user.firstName,
                user.lastName,
                user.role,
                user.selfCoefficient,
                user.socialLinks
            )

        fun fromList(users: List<UserResponse>): List<UserData> =
            users.map(::from)
    }

}