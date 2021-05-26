package ru.itis.androidtechpracticeapp.domain.usecases

import ru.itis.androidtechpracticeapp.data.api.responses.DialogInfoResponse
import ru.itis.androidtechpracticeapp.presentation.models.ChatPresentation
import ru.itis.androidtechpracticeapp.presentation.models.MessagePresentation

interface ChatUseCase {

    suspend fun getAllUserChats(userId: Int): List<ChatPresentation>

    suspend fun getChatCorrespondence(chatId: Int): List<MessagePresentation>

    suspend fun getDialogInfo(chatId: Int, userId: Int): DialogInfoResponse

    suspend fun createChat(myId: Int, anotherUserId: Int): Int

}