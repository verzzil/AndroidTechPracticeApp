package ru.itis.androidtechpracticeapp.data.api.responses
import com.google.gson.annotations.SerializedName


data class UserResponse(
    @SerializedName("birthday")
    var birthday: Long,
    @SerializedName("cash")
    var cash: Int,
    @SerializedName("email")
    var email: String,
    @SerializedName("firstName")
    var firstName: String,
    @SerializedName("fullName")
    var fullName: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("lastName")
    var lastName: String,
    @SerializedName("role")
    var role: String,
    @SerializedName("selfCoefficient")
    var selfCoefficient: Double,
    @SerializedName("socialLinks")
    var socialLinks: List<SocialLinksResponse>,
    @SerializedName("userPhotos")
    var userPhotos: List<Any>
)