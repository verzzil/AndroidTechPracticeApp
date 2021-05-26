package ru.itis.androidtechpracticeapp.data.api.responses
import com.google.gson.annotations.SerializedName


data class DialogInfoResponse(
    @SerializedName("dialogTitle")
    var dialogTitle: String,
    @SerializedName("link")
    var link: String
)