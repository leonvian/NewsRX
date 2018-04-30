package com.leonvian.news.repository

import android.content.Context
import com.lvc.newsapp.httpconnection.NewsAPI
import com.lvc.newsapp.model.News
import com.lvc.newsapp.model.NewsResult
import retrofit2.Call
import rx.Observable

const val API_KEY = "843ff35c323d4878ac20b9e402216a08"

class NewsRepository(val context: Context) {

    val newsAPI by lazy {
        NewsAPI.create()
    }

    fun getNews(country:String): Observable<List<News>> {
        return getNewsFromWeb(country)
    }

    fun getNewsFromWeb(country: String) : Observable<List<News>> {
        return newsAPI.getTopHeadlines(country, API_KEY)
                .map { result -> result.articles }
                .doOnNext{ saveDataLocalDB(it) }
    }

    suspend fun getNewsFromWebCoreroutines(country: String) : Call<NewsResult> {
        return newsAPI.getTopHeadlinesCore(country, API_KEY)

    }

    fun saveDataLocalDB(listNews: List<News>) {
        //val newsDao = NewsDatabase.getInstance(context)!!.newsDao();
        //newsDao.insert(listNews)
    }

    fun getNewsLocalDB(country: String) : Observable<List<News>> {
        return Observable.just( ArrayList<News>());
    }

}