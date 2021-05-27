package ru.itis.androidtechpracticeapp.data.models

data class PostData(
    val id: Int,
    val title: String,
    val desc: String,
    val author: UserData,
    val link: String
) {

    companion object {

    }

}
