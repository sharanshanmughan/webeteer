package com.dreamworks.webteer.screens.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.dreamworks.webteer.R
import com.dreamworks.webteer.adapter.MovieListAdapter
import com.dreamworks.webteer.databinding.FragmentMovieListBinding
import com.dreamworks.webteer.model.Movieslist
import com.dreamworks.webteer.screens.main.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : Fragment(),MovieListAdapter.OnItemClickListener {

    private lateinit var binding: FragmentMovieListBinding
    private val movieViewModel: MovieViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_movie_list,container,false)

        binding.lifecycleOwner = this
        binding.movieViewModel = movieViewModel
        binding.movieList.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.genreAutoCompleteTextView.threshold = 3


        val adapter = MovieListAdapter(this)
        movieViewModel.movieList.observe(viewLifecycleOwner) {
            adapter.setMovieList(it)
            binding.movieList.adapter = adapter
        }

        return binding.root
    }

    override fun onItemClick(movieData: Movieslist) {
        movieViewModel.movieData.value = movieData
        findNavController().navigate(R.id.action_movieLIstFragment_to_movieDetailsFragment)
    }


}