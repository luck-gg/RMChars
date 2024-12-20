package com.luckgg.rmchars.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CharacterEntity::class],
    version = 4,
)
abstract class RepoDatabase : RoomDatabase() {
    abstract val dao: CharacterDao
}
