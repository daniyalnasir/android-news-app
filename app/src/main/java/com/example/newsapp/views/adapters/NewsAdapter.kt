package com.example.newsapp.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.databinding.ItemNewsBinding
import com.example.newsapp.models.ArticleData
import com.example.newsapp.viewModels.NewsVM

class NewsAdapter(private val viewModel: NewsVM) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            ItemNewsBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(
            viewModel.newsResponse.value?.articles?.get(
                position
            )
        )

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return viewModel.newsResponse.value?.articles?.size ?: 0
    }

    //the class is holding the list view
    inner class ViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ArticleData?) {
            binding.item = item
            binding.viewModel = viewModel
            binding.executePendingBindings()
        }
    }
}