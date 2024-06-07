package com.dreamworks.webteer.screens.movieDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.dreamworks.webteer.R
import com.dreamworks.webteer.databinding.FragmentMovieDetailsBinding
import com.dreamworks.webteer.screens.main.MovieViewModel


class MovieDetailsFragment : Fragment() {


    private lateinit var binding: FragmentMovieDetailsBinding
    private val movieViewModel:MovieViewModel by activityViewModels()
    private val movieDetailsViewModel:MovieDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_movie_details,container,false)

        binding.movieDetailViewModel = movieDetailsViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        movieViewModel.movieData.observe(viewLifecycleOwner){
            movieDetailsViewModel.moviePoster.value=it.posterurl
            movieDetailsViewModel.movieName.value = it.title
            movieDetailsViewModel.getMovieGenre(it.genre)
            movieDetailsViewModel.formatDate(it.release)
            movieDetailsViewModel.movieRating.value = it.rating.toFloat()
            movieDetailsViewModel.movieDescription.value = it.desc
        }



        return binding.root
    }


}