package com.example.moviepedia.presentation.movieDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviepedia.data.remote.movieDetails.MovieDetailsResponse
import com.example.moviepedia.domain.NonPagingApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val nonPagingApiRepository: NonPagingApiRepository
) : ViewModel(){

    private val _movieId : MutableStateFlow<Int?> = MutableStateFlow(null)

    private val _movieDetails : MutableStateFlow<MovieDetailsResponse?> = MutableStateFlow(null)
    val movieDetails = _movieDetails.asStateFlow()


    fun setMovieId(id : Int){
        _movieId.value = id
    }

    fun getMovieDetails(){
        _movieId.value?.let {
            viewModelScope.launch {
                _movieDetails.value = nonPagingApiRepository.getMovieDetails(it)
            }
        }
    }

}