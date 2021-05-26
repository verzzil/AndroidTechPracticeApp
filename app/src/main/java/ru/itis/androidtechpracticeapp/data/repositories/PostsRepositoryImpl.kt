package ru.itis.androidtechpracticeapp.data.repositories

import ru.itis.androidtechpracticeapp.data.api.MyApi
import ru.itis.androidtechpracticeapp.data.api.dto.PostDto
import ru.itis.androidtechpracticeapp.data.api.responses.PostResponse
import ru.itis.androidtechpracticeapp.data.db.dao.PostDao
import ru.itis.androidtechpracticeapp.data.db.models.PostDb
import ru.itis.androidtechpracticeapp.data.models.PostData
import java.lang.Exception

class PostsRepositoryImpl(
    private val usersRepository: UsersRepository,
    private val postDao: PostDao,
    private val myApi: MyApi
) : PostsRepository {
    override suspend fun findPostById(id: Int): PostData {
        val postResponse: PostResponse?
        try {
            postResponse = myApi.getPostById(id)
        } catch (e: Exception) {
            return getReadyPost(id)
        }
        postDao.save(PostDb.from(postResponse))

        return getReadyPost(id)
    }

    override suspend fun findAllPosts(): List<PostData> {
        val postsResponse: List<PostResponse>
        try {
            postsResponse = myApi.getAllPosts()
        } catch (e: Exception) {
            return getReadyPostsList()
        }
        postDao.save(PostDb.fromList(postsResponse))

        return getReadyPostsList()
    }

    override suspend fun createPost(post: PostDto) {
        myApi.createPost(post)
    }

    private suspend fun getReadyPost(id: Int): PostData {
        val dbResponse: PostDb = postDao.findById(id)

        return PostData(
            id = dbResponse.id,
            title = dbResponse.title,
            desc = dbResponse.description,
            author = usersRepository.findById(dbResponse.authorId)
        )
    }

    private suspend fun getReadyPostsList(): List<PostData> {
        val dbResponse: List<PostDb> = postDao.findAllPosts()
        val dataResponse: List<PostData> = ArrayList()

        for (post: PostDb in dbResponse) {
            (dataResponse as ArrayList).add(
                PostData(
                    id = post.id,
                    title = post.title,
                    desc = post.description,
                    author = usersRepository.findById(post.authorId)
                )
            )
        }

        return dataResponse
    }
}