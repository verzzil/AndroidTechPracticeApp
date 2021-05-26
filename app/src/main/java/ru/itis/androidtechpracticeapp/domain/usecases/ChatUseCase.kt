package ru.itis.androidtechpracticeapp.domain.usecases

import ru.itis.androidtechpracticeapp.presentation.models.ChatPresentation
import ru.itis.androidtechpracticeapp.presentation.models.MessagePresentation

interface ChatUseCase {

    suspend fun getAllUserChats(userId: Int): List<ChatPresentation>

    suspend fun getChatCorrespondence(chatId: Int): List<MessagePresentation>

    suspend fun getTitleDialog(chatId: Int, userId: Int): String

    suspend fun createChat(myId: Int, anotherUserId: Int): Int

}