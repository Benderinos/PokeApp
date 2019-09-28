package com.david.pokeapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.david.pokeapp.livedata.BaseSingleLiveEvent
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel(){

    val showSearch : BaseSingleLiveEvent<Boolean> by lazy { BaseSingleLiveEvent<Boolean>() }

    init {
        showSearch.value = true
    }
    fun doSearch(newText: String?, homeViewModel: HomeViewModel) {
        viewModelScope.launch {
            homeViewModel.performSearch(newText)
        }
    }
}