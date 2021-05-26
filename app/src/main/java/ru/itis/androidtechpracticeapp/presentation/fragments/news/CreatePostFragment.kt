package ru.itis.androidtechpracticeapp.presentation.fragments.news

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import kotlinx.android.synthetic.main.fragment_create_post.*
import ru.itis.androidtechpracticeapp.R
import ru.itis.androidtechpracticeapp.presentation.MainActivity
import ru.itis.androidtechpracticeapp.presentation.adapters.PostsAdapter
import ru.itis.androidtechpracticeapp.presentation.models.PostPresentation
import ru.itis.androidtechpracticeapp.utils.Key
import javax.inject.Inject

class CreatePostFragment : Fragment() {

    private lateinit var navController: NavController

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: CreatePostViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (activity as MainActivity).viewModelComponent.inject(this)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(CreatePostViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_create_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getFlag().observe(viewLifecycleOwner, {
            (activity as MainActivity).onBackPressed()
        })

        create_post_send.setOnClickListener {
            if (create_post_title.text.isNotEmpty() && create_post_content.text.isNotEmpty() && create_post_link.text.isNotEmpty()) {
                viewModel.createPost(
                    create_post_title.text.toString(),
                    create_post_content.text.toString(),
                    create_post_link.text.toString(),
                    (activity as MainActivity).sp.getInt(Key.USER_ID, 0)
                )
            } else {
                Toast.makeText((activity as MainActivity), "Все поля должны быть заполнены", Toast.LENGTH_SHORT).show()
            }
        }
    }

}