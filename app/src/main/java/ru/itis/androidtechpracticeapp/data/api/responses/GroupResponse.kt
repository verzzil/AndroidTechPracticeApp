package ru.itis.androidtechpracticeapp.data.api.responses
import com.google.gson.annotations.SerializedName

data class GroupResponse(
    @SerializedName("dateOfCreation")
    var dateOfCreation: Long,
    @SerializedName("groupAct")
    var groupAct: Long,
    @SerializedName("id")
    var id: Int,
    @SerializedName("idMainUser")
    var idMainUser: Int,
    @SerializedName("title")
    var title: String,
    @SerializedName("usersIds")
    var usersIds: List<Int>
)