package com.example.myapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = arrayOf(User::class), version = 1, exportSchema = false)
abstract class userDatabase : RoomDatabase() {

    abstract fun userDao(): userDAO

    private class WordDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var userDao = database.userDao()

                    // Delete all content here.
                    userDao.deleteAll()

//                    // Add sample words.
//                    var word = Word("Hello")
//                    wordDao.insert(word)
//                    word = Word("World!")
//                    wordDao.insert(word)

                    // TODO: Add your own words!
//                    word = Word("TODO!")
//                    wordDao.insert(word)
                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: userDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): userDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    userDatabase::class.java,
                    "word_database"
                )
                    .addCallback(WordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        private class userDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            /**
             * Override the onCreate method to populate the database.
             */
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.userDao())
                    }
                }
            }
        }
        suspend fun populateDatabase(userDao: userDAO) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            userDao.deleteAll()

//            var word = Word("Hello")
//            wordDao.insert(word)
//            word = Word("World!")
//            wordDao.insert(word)
        }
    }

}
