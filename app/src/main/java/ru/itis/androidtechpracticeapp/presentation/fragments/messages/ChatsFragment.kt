package ru.itis.androidtechpracticeapp.presentation.fragments.messages

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import kotlinx.android.synthetic.main.fragment_chats.*
import ru.itis.androidtechpracticeapp.R
import ru.itis.androidtechpracticeapp.presentation.MainActivity
import ru.itis.androidtechpracticeapp.presentation.adapters.ChatsAdapter
import ru.itis.androidtechpracticeapp.presentation.models.ChatPresentation
import ru.itis.androidtechpracticeapp.utils.Consts
import ru.itis.androidtechpracticeapp.utils.Key
import javax.inject.Inject

class ChatsFragment : Fragment() {

    private lateinit var chatsAdapter: ChatsAdapter
    private var chatList: List<ChatPresentation> = ArrayList()

    companion object {
        fun newInstance() = ChatsFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: ChatsViewModel
    private lateinit var navController: NavController

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).viewModelComponent.inject(this)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(ChatsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chats, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        navController = (activity as MainActivity).navController
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSubscribes()
        initListeners()

        initUi()

    }

    override fun onPause() {
        super.onPause()
        viewModel.disconnect()
    }

    private fun initSubscribes() {
        viewModel.getChatsLiveData().observe(viewLifecycleOwner, {
            chatsAdapter.submitList(it)
        })
    }

    private fun initListeners() {
        Log.i("dfwqerqwerqwer", "${(activity as MainActivity).sp.getInt(Key.USER_ID, -50)}")
        viewModel.findAllChats((activity as MainActivity).sp.getInt(Key.USER_ID, 0))
        viewModel.startWSConnection("ws://${Consts.HOST_NAME}/wschat/${(activity as MainActivity).sp.getInt(Key.USER_ID, 0)}")
    }

    private fun initUi() {
        initAdapter()
    }

    private fun initAdapter() {
        chatsAdapter = ChatsAdapter {
            navController.navigate(
                ChatsFragmentDirections.actionChatsFragmentToCurrentChatFragment(
                    chatId = it.id, chatTitle = it.title, chatType = it.chatType
                )
            )
        }
        chats_fragment_rv_chats.adapter = chatsAdapter
    }



}
