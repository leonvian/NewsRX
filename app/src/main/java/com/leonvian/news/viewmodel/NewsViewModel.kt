package com.leonvian.news.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.content.Context
import android.util.Log
import com.lvc.newsapp.httpconnection.NewsAPI
import com.lvc.newsapp.model.News
import com.lvc.newsapp.model.NewsResult
import com.lvc.newsapp.repository.NewsRepository
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import rx.Observable
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers



class NewsViewModel(context: Application) : AndroidViewModel(context) {

    val newsRepository by lazy {
        NewsRepository(context)
    }

    /*
    remoteDataSource
    .getPhotos(photoTag)
    .flatMap({ t: FlickrPhoto ->
        Observable
            .just(serviceMapper.transform(t.photos.photo)) // <-- not lazy
    })

    newViewModel.getNews("US")
                .subscribe(object: Subscriber<NewsResult>() {
                    override fun onCompleted() {}

                    override fun onError(e: Throwable?) { }

                    override fun onNext(t: NewsResult?) {

                        Log.i("DATA", "DO ON NEXT $t.title")
                  //      if (t == null) return
                  //      myAdapter!!.setList(t.articles)

                    }
                })
     */

    /*
    fun myCoroutine() {
    launch(UI) {
        val result = async(CommonPool) {
            ...do something asynchronous...
        }.await()

        myProcessingMethod(result)
    }
}
     */

     fun getNewsCoroutines(country: String): NewsResult {
         launch(UI) {
             var result = async(CommonPool) {
                 newsRepository.getNewsFromWebCoreroutines(country)
             }.await()

         }

    }



    fun getNews(country:String) : Observable<List<News>> {
        return newsRepository.getNews(country)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }





}