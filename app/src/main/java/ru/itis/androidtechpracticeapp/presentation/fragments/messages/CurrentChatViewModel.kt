package ru.itis.androidtechpracticeapp.presentation.fragments.messages

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.*
import org.json.JSONObject
import ru.itis.androidtechpracticeapp.domain.usecases.ChatUseCase
import ru.itis.androidtechpracticeapp.presentation.models.MessagePresentation
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CurrentChatViewModel @Inject constructor(
    private val chatUseCase: ChatUseCase
) : ViewModel() {

    private val messagesLiveData: MutableLiveData<List<MessagePresentation>> = MutableLiveData()

    private lateinit var messageWebSocket: WebSocket
    private lateinit var chatsWebSocket: WebSocket
    private lateinit var messages: List<MessagePresentation>

    fun startWSConnection(url1: String, url2: String) {
        val client1 = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()
        val request1 = Request.Builder()
            .url(url1)
            .build()

        val listener1 = MyWebSocketListener()
        messageWebSocket = client1.newWebSocket(request1, listener1)

        val client2 = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()
        val request2 = Request.Builder()
            .url(url2)
            .build()

        val listener2 = MyWebSocketEchoListener()
        chatsWebSocket = client2.newWebSocket(request2, listener2)
    }

    fun getCorrespondence(chatId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                messages = chatUseCase.getChatCorrespondence(chatId)
                messagesLiveData.postValue(messages)
            }
        }
    }

    fun getMessages(): MutableLiveData<List<MessagePresentation>> = messagesLiveData

    fun sendMessage(
        chatId: Int,
        userId: Int,
        userName: String,
        text: String,
        chatType: String,
        title: String
    ) {
        val messageDto = JSONObject()
        messageDto.put("chatId", chatId)
        messageDto.put("userId", userId)
        messageDto.put("userName", userName)
        messageDto.put("text", text)

        val chatDto = JSONObject()
        chatDto.put("id", chatId)
        chatDto.put("title", title)
        chatDto.put("lastMessage", messageDto)
        chatDto.put("chatType", chatType)

        chatsWebSocket.send(chatDto.toString())
        messageWebSocket.send(messageDto.toString())
    }

    fun disconnect() {
        chatsWebSocket.cancel()
        messageWebSocket.cancel()
    }

    inner class MyWebSocketListener : WebSocketListener() {
        override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
            super.onClosed(webSocket, code, reason)
            Log.i("datafrommyserver", "closd")

        }

        override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
            super.onClosing(webSocket, code, reason)
            Log.i("datafrommyserver", "closing")

        }

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
            super.onFailure(webSocket, t, response)
            t.printStackTrace()
            Log.i("datafrommyserver", "failure")
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            Log.i("datafrommyserver", "${text}")
            (messages as ArrayList).add(getMessage(JSONObject(text)))
            messagesLiveData.postValue(messages)
        }

        override fun onOpen(webSocket: WebSocket, response: Response) {
            super.onOpen(webSocket, response)
            Log.i("datafrommyserver", "OPENED! ${webSocket}")

        }

        private fun getMessage(json: JSONObject): MessagePresentation =
            MessagePresentation(
                json.getString("text"),
                json.getLong("sendDate"),
                json.getInt("chatId"),
                json.getInt("userId"),
                json.getString("userName")
            )
    }

    inner class MyWebSocketEchoListener : WebSocketListener() {
        override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
            super.onClosed(webSocket, code, reason)
            Log.i("datafrommyserver", "closd")

        }

        override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
            super.onClosing(webSocket, code, reason)
            Log.i("datafrommyserver", "closing")

        }

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
            super.onFailure(webSocket, t, response)
            t.printStackTrace()
            Log.i("datafrommyserver", "failure")
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            Log.i("datafrommyserver", "${text}")
        }

        override fun onOpen(webSocket: WebSocket, response: Response) {
            super.onOpen(webSocket, response)
            Log.i("datafrommyserver", "OPENED! ${webSocket}")

        }
    }


}