package ru.itis.androidtechpracticeapp.data.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity(tableName = "user_acts")
data class UserActsDb(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val actStatus: String,
    val dateOfCreation: Long,
    val dateOfEnd: Long,
    val latitude: Double,
    val longitude: Double,
    val reward: Long,
    val title: String,
    val userId: Int
)
