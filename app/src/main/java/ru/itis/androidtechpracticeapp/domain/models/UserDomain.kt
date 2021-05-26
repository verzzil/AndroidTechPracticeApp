package ru.itis.androidtechpracticeapp.domain.models

import ru.itis.androidtechpracticeapp.data.api.responses.SocialLinksResponse
import ru.itis.androidtechpracticeapp.data.db.models.UserDb
import ru.itis.androidtechpracticeapp.data.models.UserData

data class UserDomain(
    var id: Int = 0,
    var birthday: Long,
    var cash: Int,
    var email: String,
    var firstName: String,
    var lastName: String,
    var role: String,
    var selfCoefficient: Double,
    var socialLinks: List<SocialLinksResponse>,
    var photoLink: String?
) {

    companion object {
        fun from(user: UserData): UserDomain =
            UserDomain(
                user.id,
                user.birthday,
                user.cash,
                user.email,
                user.firstName,
                user.lastName,
                user.role,
                user.selfCoefficient,
                user.socialLinks,
                user.photoLink
            )

        fun fromList(users: List<UserData>): List<UserDomain> =
            users.map(::from)
    }

}
