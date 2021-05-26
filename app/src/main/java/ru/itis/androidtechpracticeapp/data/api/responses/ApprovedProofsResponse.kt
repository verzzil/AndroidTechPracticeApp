package ru.itis.androidtechpracticeapp.data.api.responses
import com.google.gson.annotations.SerializedName


data class ApprovedProofsResponse(
    @SerializedName("actId")
    var actId: Any,
    @SerializedName("id")
    var id: Int,
    @SerializedName("latitude")
    var latitude: Double,
    @SerializedName("longitude")
    var longitude: Double,
    @SerializedName("moderatorId")
    var moderatorId: Int,
    @SerializedName("photoLink")
    var photoLink: Any,
    @SerializedName("sendDate")
    var sendDate: Long,
    @SerializedName("state")
    var state: Any,
    @SerializedName("text")
    var text: String,
    @SerializedName("type")
    var type: Any
)