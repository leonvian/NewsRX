package com.leonvian.news.views

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lvc.newsapp.R
import com.lvc.newsapp.model.News
import kotlinx.android.synthetic.main.news_item_simple.view.*

class NewsAdapter(var listNews: List<News>, val listener: (News) -> Unit) : RecyclerView.Adapter<NewsAdapter.NewsItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemHolder {
        val itensViewHolder = NewsItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.news_item_simple, parent, false))
        return itensViewHolder
    }

    override fun getItemCount() = listNews.size

    override fun onBindViewHolder(holder: NewsItemHolder, position: Int) {
        val news = listNews[position];
        holder.bind(news, listener)
    }

    fun setList(listNews: List<News>) {
        this.listNews = listNews;
        notifyDataSetChanged()
    }

    class NewsItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(news: News, listener: (News) -> Unit) = with(itemView) {
            textViewTitle.text = news.title
            textViewDescription.text = news.description
            setOnClickListener{listener}
        }

    }
}