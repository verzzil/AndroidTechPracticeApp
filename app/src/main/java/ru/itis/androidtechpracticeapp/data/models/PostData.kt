package ru.itis.androidtechpracticeapp.data.models

data class PostData(
    val id: Int,
    val title: String,
    val desc: String,
    val author: UserData,
) {

    companion object {

    }

}
