package ru.itis.androidtechpracticeapp.data.repositories

import ru.itis.androidtechpracticeapp.presentation.models.ChatPresentation
import ru.itis.androidtechpracticeapp.presentation.models.MessagePresentation

interface ChatRepository {

    suspend fun getAllUserChats(userId: Int): List<ChatPresentation>

    suspend fun getChatCorrespondence(chatId: Int): List<MessagePresentation>

}