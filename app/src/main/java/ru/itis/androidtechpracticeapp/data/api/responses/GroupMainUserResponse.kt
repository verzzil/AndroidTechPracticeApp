package ru.itis.androidtechpracticeapp.data.api.responses
import com.google.gson.annotations.SerializedName


data class GroupMainUserResponse(
    @SerializedName("idMainUser")
    var idMainUser: Int
)