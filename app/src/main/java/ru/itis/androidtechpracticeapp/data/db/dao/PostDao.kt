package ru.itis.androidtechpracticeapp.data.db.dao

import androidx.room.*
import ru.itis.androidtechpracticeapp.data.db.models.PostDb

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(post: PostDb)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(posts: List<PostDb>)

    @Query(value = "select * from posts where id = :postId limit 1")
    fun findById(postId: Int): PostDb

    @Query(value = "select * from posts")
    fun findAllPosts(): List<PostDb>

    @Transaction
    @Query(value = "delete from posts")
    fun clearDb()

}