package ru.itis.androidtechpracticeapp.presentation.fragments.news

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_current_news.*
import ru.itis.androidtechpracticeapp.R
import ru.itis.androidtechpracticeapp.presentation.MainActivity
import javax.inject.Inject

class CurrentNewsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: CurrentNewsViewModel

    val args: CurrentNewsFragmentArgs by navArgs()

    companion object {
        fun newInstance() = CurrentNewsFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (activity as MainActivity).viewModelComponent.inject(this)

        viewModel =
            ViewModelProvider(this, viewModelFactory).get(CurrentNewsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_current_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        initListeners()
    }

    private fun initObservers() {
        viewModel.getPost().observe(viewLifecycleOwner, {
            current_news_title.text = it.title
            current_news_content.text = it.desc
            current_news_author.text = it.author.getFullName()
        })
    }

    private fun initListeners() {
        viewModel.searchPost(args.postId)
    }

}