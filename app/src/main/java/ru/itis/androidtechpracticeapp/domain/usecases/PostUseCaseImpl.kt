package ru.itis.androidtechpracticeapp.domain.usecases

import ru.itis.androidtechpracticeapp.data.api.dto.PostDto
import ru.itis.androidtechpracticeapp.data.api.responses.SocialLinksResponse
import ru.itis.androidtechpracticeapp.data.repositories.PostsRepository
import ru.itis.androidtechpracticeapp.domain.models.PostDomain
import ru.itis.androidtechpracticeapp.presentation.models.PostPresentation
import ru.itis.androidtechpracticeapp.presentation.models.UserPresentation
import java.lang.Exception

class PostUseCaseImpl(
    private val postsRepository: PostsRepository,
) : PostUseCase {

    override suspend fun findPostById(id: Int): PostPresentation {
        val result: PostDomain = PostDomain.from(postsRepository.findPostById(id))

        return PostPresentation.from(result)
    }

    override suspend fun findAllPosts(): List<PostPresentation> {
        val result: List<PostDomain> = PostDomain.fromList(postsRepository.findAllPosts())

        return PostPresentation.fromList(result.sortedByDescending { postDomain -> postDomain.id })
    }

    override suspend fun createPost(post: PostDto) {
        postsRepository.createPost(post)
    }
}