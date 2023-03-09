package com.example.myapp.db

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class userRepository(private val wordDao: userDAO) {
    val allWords: Flow<List<User>> = wordDao.getAllUsers()
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(user: User) {
        wordDao.insert(user)
    }
}