package ru.itis.androidtechpracticeapp.presentation.models

data class ChatPresentation(
    val id: Int,
    val title: String,
    val chatType: String,
    var lastMessage: MessagePresentation
) {
    companion object {
        fun cloneData(list: List<ChatPresentation>): List<ChatPresentation> {
            val result: List<ChatPresentation> = ArrayList()
            for (pres: ChatPresentation in list) {
                (result as ArrayList).add(clone(pres))
            }
            return result
        }
        fun clone(pres: ChatPresentation): ChatPresentation =
            ChatPresentation(
                pres.id,
                pres.title,
                pres.chatType,
                MessagePresentation.clone(pres.lastMessage)
            )
    }
}
