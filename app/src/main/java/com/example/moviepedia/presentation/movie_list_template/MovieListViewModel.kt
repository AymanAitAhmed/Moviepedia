package com.example.moviepedia.presentation.movie_list_template

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviepedia.data.datastore.LayoutType
import com.example.moviepedia.domain.PagingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val layoutTypeDatastore: LayoutType,
    private val pagingRepository: PagingRepository
) : ViewModel() {

    private val _layoutType = MutableStateFlow(0)
    val layoutType = _layoutType.asStateFlow()

    val popularMovies = pagingRepository.getAllMovies()

    fun getCurrentLayoutType() {
        viewModelScope.launch {
            layoutTypeDatastore.currentLayoutType.collect { layoutType ->
                _layoutType.value = layoutType
            }
        }
    }

    fun flipLayoutType(){
        viewModelScope.launch {
            layoutTypeDatastore.flipCurrentLayout()
        }
    }

}