package com.leonvian.news.model

/*
@Entity
data class News(
        @PrimaryKey(autoGenerate = true) val id: Long,
        @ColumnInfo val author: String,
        @ColumnInfo val title: String,
        @ColumnInfo val description: String,
        @ColumnInfo val url: String,
        @ColumnInfo val urlToImage: String,
        @ColumnInfo val publishedAt: String,
        @ColumnInfo val source: Source
) */


data class News(
        val id: Long,
        val author: String,
        val title: String,
        val description: String,
        val url: String,
        val urlToImage: String,
        val publishedAt: String,
        val source: Source
)

data class NewsResult(
        val status:String,
        val totalResult: Int,
        val articles: List<News>
)

data class Source(
        val id: String,
        val name: String
)

data class Category(val news: List<News>,
                    val title:String)