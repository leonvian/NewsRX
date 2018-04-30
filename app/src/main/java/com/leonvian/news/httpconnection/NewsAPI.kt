package com.leonvian.news.httpconnection

import com.lvc.newsapp.model.NewsResult
import retrofit2.Call
import retrofit2.Retrofit

import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable


interface NewsAPI {

    //https://newsapi.org/v2/top-headlines?country=us&apiKey=843ff35c323d4878ac20b9e402216a08
    companion object {
        fun create(): NewsAPI {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://newsapi.org/v2/")
                    .build()

            return retrofit.create(NewsAPI::class.java)
        }
    }

    @GET("top-headlines")
    fun getTopHeadlines(@Query("country") country:String, @Query("apiKey") apiKey:String) : Observable<NewsResult>

    @GET("top-headlines")
    suspend fun getTopHeadlinesCore(@Query("country") country:String, @Query("apiKey") apiKey:String) : Call<NewsResult>




}