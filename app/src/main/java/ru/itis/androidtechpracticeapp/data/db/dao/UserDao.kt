package ru.itis.androidtechpracticeapp.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.itis.androidtechpracticeapp.data.db.models.UserDb

@Dao
interface UserDao {

    @Query("select * from user_db where id = :id limit 1")
    fun findById(id: Int): UserDb

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(user: UserDb)

}