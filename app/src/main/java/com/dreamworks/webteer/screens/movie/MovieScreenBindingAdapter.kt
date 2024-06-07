package com.dreamworks.webteer.screens.movie

import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.ImageLoader
import coil.disk.DiskCache
import coil.load
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.size.Size
import com.dreamworks.webteer.R

@BindingAdapter("setGenre")
fun setGenre(autoCompleteTextView: AutoCompleteTextView,genres: List<String>){
    val adapter: ArrayAdapter<String> =
        ArrayAdapter<String>(autoCompleteTextView.context , R.layout.dropdown_item, genres)
    autoCompleteTextView.threshold = 3
    autoCompleteTextView.setAdapter(adapter)
}

@BindingAdapter("currentGenre")
fun setGenreListener(autoCompleteTextView: AutoCompleteTextView,listener:((String) -> Unit)?){
    autoCompleteTextView.setOnItemClickListener { adapterView, _, i, _ ->
        val selectedItem = adapterView.getItemAtPosition(i) as String
        listener?.invoke(selectedItem)
    }
}

@BindingAdapter("setImage")
fun setImage(imageView: ImageView,url:String){

    imageView.load(url){
        crossfade(true)
        placeholder(R.drawable.loading)
        error(R.drawable.error)
    }
}