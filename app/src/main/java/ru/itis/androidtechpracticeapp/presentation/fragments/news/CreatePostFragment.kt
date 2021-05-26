package ru.itis.androidtechpracticeapp.presentation.fragments.news

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.itis.androidtechpracticeapp.R

class CreatePostFragment : Fragment() {

    companion object {
        fun newInstance() = CreatePostFragment()
    }

    private lateinit var viewModel: CreatePostViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_create_post, container, false)
    }

}