package ru.itis.androidtechpracticeapp.presentation.fragments.news

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.fragment_news.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import ru.itis.androidtechpracticeapp.R
import ru.itis.androidtechpracticeapp.data.repositories.PostsRepository
import ru.itis.androidtechpracticeapp.presentation.MainActivity
import ru.itis.androidtechpracticeapp.presentation.adapters.PostsAdapter
import ru.itis.androidtechpracticeapp.presentation.itemDecorators.NewsItemDecorator
import ru.itis.androidtechpracticeapp.presentation.models.PostPresentation
import javax.inject.Inject

class NewsFragment : Fragment(), CoroutineScope by CoroutineScope(Dispatchers.IO) {

    private lateinit var rvNews: PostsAdapter
    private lateinit var navController: NavController

    private var postsList: List<PostPresentation> = ArrayList()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var postsRepository: PostsRepository

    companion object {
        fun newInstance() = NewsFragment()
    }

    private lateinit var viewModel: NewsViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (activity as MainActivity).viewModelComponent.inject(this)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(NewsViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        navController = (activity as MainActivity).navController
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setNewsAdapter()

        initObservers()
        initListeners()
    }

    private fun initObservers() {
        viewModel.getPosts().observe(viewLifecycleOwner, {
            rvNews.submitList(it)
        })
    }

    private fun initListeners() {
        viewModel.searchPosts()
    }

    private fun setNewsAdapter() {
        rvNews = PostsAdapter {
            navController.navigate(NewsFragmentDirections.actionNewsFragmentToCurrentNewsFragment(it.id))
        }
        rvNews.submitList(postsList)
        rv_news.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        rv_news.addItemDecoration(NewsItemDecorator(20))
        rv_news.adapter = rvNews
    }


}