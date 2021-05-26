package ru.itis.androidtechpracticeapp.data.api.responses
import com.google.gson.annotations.SerializedName


data class TitleDialog(
    @SerializedName("dialogTitle")
    var dialogTitle: String
)