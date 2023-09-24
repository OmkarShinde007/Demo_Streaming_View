package com.demo.demostramingapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.demostramingapp.model.JsonResponse
import com.demo.demostramingapp.model.MovieListFileName
import kotlinx.coroutines.launch
import javax.inject.Inject

/** ViewModel used to provide the data to the different views. */
class MyViewModel @Inject constructor(private val repository: MyRepository) : ViewModel() {

    val combinedData: LiveData<List<JsonResponse>> = repository.combinedData

    fun loadData() {
        // Load all data for the three datasets and await completion
        viewModelScope.launch {
            repository.loadAllDatasets(
                MovieListFileName.PAGE1.fileName,
                MovieListFileName.PAGE2.fileName,
                MovieListFileName.PAGE3.fileName
            )
            repository.awaitAllDataLoaded()
        }
    }
}
