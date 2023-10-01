package com.example.moviepedia.presentation.movie_list_template

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviepedia.data.LayoutType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val layoutTypeDatastore: LayoutType
) : ViewModel() {

    private val _layoutType = MutableStateFlow(0)
    val layoutType = _layoutType.asStateFlow()

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