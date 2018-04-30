package com.leonvian.news.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.lvc.newsapp.R
import com.lvc.newsapp.model.Category
import com.lvc.newsapp.model.News
import com.lvc.newsapp.model.Source
import com.lvc.newsapp.testing.Exectioner
import com.lvc.newsapp.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.news_main_layout.*
import rx.Subscriber

class NewsMainActivity : AppCompatActivity() {

    val newViewModel by lazy {
        NewsViewModel(this.application)
    }

    var categoryList:List<Category> = ArrayList<Category>()
    var myAdapter: CategoryAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.news_main_layout)

        recyclerViewMain.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        categoryList = createFakeCategories()
        myAdapter = CategoryAdapter(categoryList, this)

        recyclerViewMain.adapter = myAdapter

      /*  newViewModel.getNews("US")
                .subscribe(object: Subscriber<List<News>>() {
                    override fun onCompleted() {}
                    override fun onError(e: Throwable?) { }
                    override fun onNext(listNews: List<News>?) {
                        Log.i("DATA", "ON NEXT FINAL ${listNews!!.size} ")
                    }
                }) */

        newViewModel.getNewsCoroutines("US")

        /*
        Exectioner.executeThings().subscribe {
            Log.i("MARCH", "$it -UI- ${Thread.currentThread().name}");
        } */
    }


    fun createFakeCategories() : List<Category> {
        val categories = ArrayList<Category>()
        for (i in 1..10) {
            val category = Category(createFakeNews(), "Title $i")
            categories.add(category)
        }

        return categories
    }

    fun createFakeNews() : List<News> {
        val listNews = ArrayList<News>()
        for (i in 1..10) {
            val news = News(10,"Leonardo", "Title $i", "Description $i", "", "", "", Source("id", "name"))
            listNews.add(news)
        }

        return listNews
    }


}
