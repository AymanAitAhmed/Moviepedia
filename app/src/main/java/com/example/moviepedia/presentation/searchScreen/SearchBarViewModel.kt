package com.example.moviepedia.presentation.searchScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import com.example.moviepedia.domain.PagingRepository
import com.example.moviepedia.domain.model.MovieEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchBarViewModel @Inject constructor(
    private val pagingRepository: PagingRepository
) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()

    private val _searchedMoviesList : MutableStateFlow<PagingData<MovieEntity>> = MutableStateFlow(
        PagingData.empty()
    )
    val searchedMoviesList = _searchedMoviesList.asStateFlow()

    private var lastSearchedQuery = ""
    fun onQueryChange(query : String){
        _query.value = query
    }

    fun onSearch(query: String){
        if (query.isEmpty()||query.isBlank()){
            return
        }
        if (query.trim() == lastSearchedQuery){
            return
        }
        viewModelScope.launch {
            lastSearchedQuery = query.trim()
            pagingRepository.searchMovies(coroutineScope = viewModelScope,query = query).collect{
                _searchedMoviesList.value = it
            }
        }
    }

    fun onClear(){
        _query.value = ""
    }

}