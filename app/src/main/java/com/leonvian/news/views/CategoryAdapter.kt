package com.leonvian.news.views

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lvc.newsapp.R
import com.lvc.newsapp.model.Category
import kotlinx.android.synthetic.main.category_item_layout.view.*


class CategoryAdapter(var categoryList: List<Category>, val context: Context) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    val recycledViewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val categoryViewHolder = CategoryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.category_item_layout, parent, false))
        return categoryViewHolder
    }

    override fun getItemCount(): Int = categoryList.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categoryList[position]
        holder.bind(category, recycledViewPool)
    }

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(category: Category, pool: RecyclerView.RecycledViewPool) = with(itemView) {
            itemTitle.text = category.title
            recyclerViewNews.setHasFixedSize(true)
            recyclerViewNews.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            recyclerViewNews.recycledViewPool = pool
            recyclerViewNews.adapter = NewsAdapter(category.news) { }
        }


    }
}


