package ru.itis.androidtechpracticeapp.domain.usecases

import ru.itis.androidtechpracticeapp.data.repositories.ChatRepository
import ru.itis.androidtechpracticeapp.presentation.models.ChatPresentation
import ru.itis.androidtechpracticeapp.presentation.models.MessagePresentation

class ChatUseCaseImpl(
    private val chatRepository: ChatRepository
) : ChatUseCase {
    override suspend fun getAllUserChats(userId: Int): List<ChatPresentation> {
        return chatRepository.getAllUserChats(userId)
    }

    override suspend fun getChatCorrespondence(chatId: Int): List<MessagePresentation> {
        return chatRepository.getChatCorrespondence(chatId)
    }
}