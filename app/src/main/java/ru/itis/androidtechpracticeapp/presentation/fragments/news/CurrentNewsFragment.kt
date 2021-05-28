package ru.itis.androidtechpracticeapp.presentation.fragments.news

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_current_news.*
import ru.itis.androidtechpracticeapp.R
import ru.itis.androidtechpracticeapp.presentation.MainActivity
import java.lang.Exception
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
        savedInstanceState: Bundle?,
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
            if (it.bitmap != null) {
                current_news_image.setImageBitmap(it.bitmap)
            } else {
                current_news_image.setImageResource(R.drawable.mock_post)
            }
        })
        viewModel.getErrors().observe(viewLifecycleOwner, {
            Toast.makeText((activity as MainActivity),
                "Нет интернет соединения",
                Toast.LENGTH_SHORT).show()
        })
    }

    private fun initListeners() {
        viewModel.searchPost(args.postId)
    }

}