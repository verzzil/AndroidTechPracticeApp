package ru.itis.androidtechpracticeapp.data.api.responses
import com.google.gson.annotations.SerializedName

data class ActResponse(
    @SerializedName("actStatus")
    var actStatus: String,
    @SerializedName("dateOfCreation")
    var dateOfCreation: Long,
    @SerializedName("dateOfEnding")
    var dateOfEnding: Long,
    @SerializedName("id")
    var id: Int,
    @SerializedName("reward")
    var reward: Int,
    @SerializedName("title")
    var title: String,
    @SerializedName("type")
    var type: String,
    @SerializedName("foreignId")
    var foreignId: Int,
    var isAdmin: Boolean?
)