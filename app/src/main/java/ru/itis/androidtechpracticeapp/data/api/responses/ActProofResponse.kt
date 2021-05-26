package ru.itis.androidtechpracticeapp.data.api.responses
import com.google.gson.annotations.SerializedName

data class ActProofResponse(
    @SerializedName("actId")
    var actId: Int,
    @SerializedName("id")
    var id: Int,
    @SerializedName("moderatorId")
    var moderatorId: Int,
    @SerializedName("photoLink")
    var photoLink: String,
    @SerializedName("sendDate")
    var sendDate: Long,
    @SerializedName("state")
    var state: String,
    @SerializedName("text")
    var text: String,
    @SerializedName("type")
    var type: String
)