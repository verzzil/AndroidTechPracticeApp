package ru.itis.androidtechpracticeapp.data.api.responses
import com.google.gson.annotations.SerializedName


data class SocialLinksResponse(
    @SerializedName("id")
    var id: Int,
    @SerializedName("socialLink")
    var socialLink: String,
    @SerializedName("titleOfSocialRecourse")
    var titleOfSocialRecourse: String,
    @SerializedName("userId")
    var userId: Int
)