package ru.itis.androidtechpracticeapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.itis.androidtechpracticeapp.data.db.dao.MessageDao
import ru.itis.androidtechpracticeapp.data.db.dao.PostDao
import ru.itis.androidtechpracticeapp.data.db.dao.UserDao
import ru.itis.androidtechpracticeapp.data.db.models.MessageDb
import ru.itis.androidtechpracticeapp.data.db.models.PostDb
import ru.itis.androidtechpracticeapp.data.db.models.UserActsDb
import ru.itis.androidtechpracticeapp.data.db.models.UserDb

@Database(
    entities = [PostDb::class, MessageDb::class, UserActsDb::class, UserDb::class],
    version = 3,
    exportSchema = false
)
abstract class AppDb: RoomDatabase() {
    abstract fun getUserDao(): UserDao
    abstract fun getMessageDao(): MessageDao
    abstract fun getPostDao(): PostDao
}