package ru.itis.androidtechpracticeapp.data.api.responses

import com.google.gson.annotations.SerializedName

data class PostResponse(
    @SerializedName("authorId")
    var authorId: Int,
    @SerializedName("content")
    var content: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("title")
    var title: String
) {

}
