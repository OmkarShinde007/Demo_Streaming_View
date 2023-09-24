package com.demo.demostramingapp.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.demostramingapp.R
import com.demo.demostramingapp.model.Content
import java.util.Locale

/** Adapter used to hold and provide data to Recyclerview. */
class MovieListAdapter(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val movieList = ArrayList<Content>()
    private val filterList = ArrayList<Content>()
    private val context = context
    private var searchQuery: String = ""

    init {
        filterList.addAll(movieList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MovieListViewHolder(
            LayoutInflater.from(context).inflate(R.layout.movie_list_item_view, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return filterList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // Bind the data to the recyclerview
        (holder as MovieListViewHolder).bind(filterList[position], searchQuery)
    }

    /** Method used to update the list and notify the recyclerview items. */
    fun updateList(newList: List<Content>) {
        movieList.addAll(newList)
        filterList.addAll(newList)
        notifyDataSetChanged()
    }

    /** Method used to provide the filter list as per the search query. */
    fun updateFilterList(newList: String?) {
        searchQuery = newList.toString()
        filterList.clear()
        if (newList!!.isBlank()) {
            filterList.addAll(movieList)
        } else {
            val lowercaseQuery = newList.toLowerCase(Locale.getDefault())
            movieList.forEach { item ->
                if (item.name.toLowerCase(Locale.getDefault()).contains(lowercaseQuery) ||
                    item.name.toLowerCase(Locale.getDefault()).contains(lowercaseQuery)
                ) {
                    filterList.add(item)
                }
            }
        }
        notifyDataSetChanged()
    }
}