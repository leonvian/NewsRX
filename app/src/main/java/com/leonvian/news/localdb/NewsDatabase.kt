package com.leonvian.news.localdb

/*
@Database(entities = arrayOf(News::class), version = 1)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao

    companion object {
        private var INSTANCE: NewsDatabase? = null

        fun getInstance(context: Context): NewsDatabase? {
            if (INSTANCE == null) {
                synchronized(NewsDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NewsDatabase::class.java, "news.db")
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
} */