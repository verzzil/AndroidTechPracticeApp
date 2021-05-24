package ru.itis.androidtechpracticeapp.data.api.responses
import com.google.gson.annotations.SerializedName

data class ActResponse(
    @SerializedName("actStatus")
    var actStatus: String,
    @SerializedName("dateOfCreation")
    var dateOfCreation: Int,
    @SerializedName("dateOfEnding")
    var dateOfEnding: Int,
    @SerializedName("id")
    var id: Int,
    @SerializedName("reward")
    var reward: Int,
    @SerializedName("title")
    var title: String,
    @SerializedName("type")
    var type: String,
    var isAdmin: Boolean?
)