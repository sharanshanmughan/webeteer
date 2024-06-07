package com.dreamworks.webteer.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dreamworks.webteer.databinding.AdapterMovieListBinding
import com.dreamworks.webteer.model.MovieData
import com.dreamworks.webteer.model.Movieslist

class MovieListAdapter(private val onItemClickListener: OnItemClickListener):RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    private var movieList:List<Movieslist> = arrayListOf()

    fun setMovieList(movieList:List<Movieslist>){
        this.movieList = movieList
    }

    class ViewHolder(val binding: AdapterMovieListBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = AdapterMovieListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.item = movieList[position]

        holder.binding.root.setOnClickListener {
            onItemClickListener.onItemClick(movieList[position])

        }

    }

    interface OnItemClickListener {
        fun onItemClick(movieData: Movieslist)
    }



}