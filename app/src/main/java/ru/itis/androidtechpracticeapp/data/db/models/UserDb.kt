package ru.itis.androidtechpracticeapp.data.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.itis.androidtechpracticeapp.data.api.responses.UserResponse

@Entity(tableName = "user_db")
data class UserDb(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var birthday: Long,
    var cash: Int,
    var email: String,
    var firstName: String,
    var fullName: String,
    var lastName: String,
    var role: String,
    var selfCoefficient: Double,
) {

    companion object {
        fun from(user: UserResponse): UserDb =
            UserDb(
                user.id,
                user.birthday,
                user.cash,
                user.email,
                user.firstName,
                user.fullName,
                user.lastName,
                user.role,
                user.selfCoefficient
            )

        fun fromList(users: List<UserResponse>): List<UserDb> =
            users.map(::from)
    }

}