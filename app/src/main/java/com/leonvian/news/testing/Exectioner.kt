package com.leonvian.news.testing

import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

object Exectioner {


    fun executeThings() : Observable<String> {
        return Observable.just("Hello World")
                .doOnNext({ ActionOne().execute()})

                .observeOn(Schedulers.io())
                .doOnNext({ ActionTwo().execute()})

                .observeOn(Schedulers.computation())
                .doOnNext({ ActionThree().execute()})

                .observeOn(Schedulers.io())
                .doOnNext({ ActionFour().execute()})

                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
    }

}