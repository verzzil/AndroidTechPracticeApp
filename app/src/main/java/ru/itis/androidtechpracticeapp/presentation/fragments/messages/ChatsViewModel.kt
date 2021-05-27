package ru.itis.androidtechpracticeapp.presentation.fragments.messages

import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.*
import org.json.JSONObject
import retrofit2.http.Url
import ru.itis.androidtechpracticeapp.domain.usecases.ChatUseCase
import ru.itis.androidtechpracticeapp.presentation.models.ChatPresentation
import ru.itis.androidtechpracticeapp.presentation.models.MessagePresentation
import java.lang.Exception
import java.net.URL
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ChatsViewModel @Inject constructor(
    private val chatUseCase: ChatUseCase,
) : ViewModel() {
    private val chatsLiveData: MutableLiveData<List<ChatPresentation>> = MutableLiveData()
    private var chats: List<ChatPresentation> = ArrayList()
    private lateinit var webSocket: WebSocket
    private val errors: MutableLiveData<Exception> = MutableLiveData()

    fun findAllChats(userId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val result = chatUseCase.getAllUserChats(userId)
                    for (cp: ChatPresentation in result) {
                        if (cp.chatType != "GROUP") {
                            val response = chatUseCase.getDialogInfo(cp.id, userId)
                            cp.title = response.dialogTitle
                            if (response.link != null && response.link.isNotEmpty()) {
                                cp.link =
                                    BitmapFactory.decodeStream(URL(response.link).openConnection()
                                        .getInputStream())
                            }
                        }
                    }
                    chats = ChatPresentation.cloneData(result)
                    chatsLiveData.postValue(
                        result
                    )
                } catch (e: Exception) {
                    errors.postValue(e)
                }
            }
        }
    }

    fun startWSConnection(url: String) {
        try {
            val client = OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .build()
            val request = Request.Builder()
                .header("Connection", "close")
                .url(url)
                .build()

            val listener = MyWebSocketListener()
            webSocket = client.newWebSocket(request, listener)
        } catch (e : Exception) {
            errors.postValue(e)
        }
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
            getNewMessage(JSONObject(text))
        }

        override fun onOpen(webSocket: WebSocket, response: Response) {
            super.onOpen(webSocket, response)
            Log.i("datafrommyserver", "OPENED! ${webSocket}")

        }

        private fun getNewMessage(json: JSONObject) {
            val newChatPresent = json.getJSONObject("lastMessage").let {
                ChatPresentation(
                    json.getInt("id"),
                    json.getString("title"),
                    json.getString("chatType"),
                    MessagePresentation(
                        it.getString("text"),
                        it.getLong("sendDate"),
                        it.getInt("chatId"),
                        it.getInt("userId"),
                        it.getString("userName")
                    ),
                    null
                )
            }


            var flag = false
            for (chat: ChatPresentation in chats) {
                if (chat.id == newChatPresent.id) {
                    chat.lastMessage = newChatPresent.lastMessage
                    flag = true
                    break
                }
            }
            if (!flag) {
                (chats as ArrayList).add(newChatPresent)
            }
            chatsLiveData.postValue(
                ChatPresentation.cloneData(chats).sortedBy { it.lastMessage.sendDate }.reversed()
            )
        }
    }

    fun disconnect() {
        webSocket.cancel()
    }

    fun getChatsLiveData(): MutableLiveData<List<ChatPresentation>> = chatsLiveData
    fun getErrors(): MutableLiveData<Exception> = errors

}