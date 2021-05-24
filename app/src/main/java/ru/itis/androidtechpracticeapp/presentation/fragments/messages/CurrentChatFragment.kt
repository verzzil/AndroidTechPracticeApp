package ru.itis.androidtechpracticeapp.presentation.fragments.messages

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_current_chat.*
import ru.itis.androidtechpracticeapp.R
import ru.itis.androidtechpracticeapp.presentation.MainActivity
import ru.itis.androidtechpracticeapp.presentation.ToggleBars
import ru.itis.androidtechpracticeapp.presentation.adapters.CorrespondenceAdapter
import ru.itis.androidtechpracticeapp.utils.Consts
import ru.itis.androidtechpracticeapp.utils.Key
import java.util.*
import javax.inject.Inject

class CurrentChatFragment : Fragment() {

    val args: CurrentChatFragmentArgs by navArgs()

    companion object {
        fun newInstance() = CurrentChatFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: CurrentChatViewModel

    private lateinit var rvCorrespondenceAdapter: CorrespondenceAdapter

    private lateinit var closeNavBar: ToggleBars

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).viewModelComponent.inject(this)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(CurrentChatViewModel::class.java)

        closeNavBar = (activity as MainActivity)
        closeNavBar.hideBottomBar()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_current_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        initListeners()

        initUi()

    }

    override fun onPause() {
        super.onPause()
        closeNavBar.showBottomBar()
    }

    private fun initObservers() {
        viewModel.getMessages().observe(viewLifecycleOwner, {
            rvCorrespondenceAdapter.submitList(it)
            rv_current_chat.scrollToPosition(rvCorrespondenceAdapter.itemCount - 1)
        })
    }

    private fun initListeners() {
        viewModel.getCorrespondence(args.chatId)
        viewModel.startWSConnection(
            "ws://${Consts.HOST_NAME}/wschat/user/${
                (activity as MainActivity).sp.getInt(
                    Key.USER_ID,
                    0
                )
            }/chat/${args.chatId}",
            "ws://${Consts.HOST_NAME}/wschat/${UUID.randomUUID()}"
        )

        current_chat_btn_msg.setOnClickListener {
            viewModel.sendMessage(
                args.chatId,
                (activity as MainActivity).sp.getInt(Key.USER_ID, 0),
                (activity as MainActivity).sp.getString(Key.USER_NAME, "").orEmpty(),
                current_chat_et_msg.text.toString(),
                args.chatType,
                args.chatTitle
            )
        }

    }

    private fun initUi() {
        initAdapter()
    }

    private fun initAdapter() {
        rvCorrespondenceAdapter = CorrespondenceAdapter((activity as MainActivity).sp.getInt(Key.USER_ID, 0))
        rv_current_chat.adapter = rvCorrespondenceAdapter
    }

}