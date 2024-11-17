package com.luckgg.rmchars.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CharacterEntity::class, RemoteKeys::class],
    version = 2,
)
abstract class RepoDatabase : RoomDatabase() {
    abstract val dao: CharacterDao
    abstract val remoteKeysDao: RemoteKeysDao
}
