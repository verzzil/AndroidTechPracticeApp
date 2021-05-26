package ru.itis.androidtechpracticeapp.presentation.models

import ru.itis.androidtechpracticeapp.data.api.responses.SocialLinksResponse
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
    var socialLinks: List<SocialLinksResponse>,
) {

    companion object {

        fun from(user: UserDomain): UserPresentation =
            UserPresentation(
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

        fun fromList(users: List<UserDomain>): List<UserPresentation> =
            users.map(::from)
    }

    fun getFullName(): String =
        "$firstName $lastName"
}