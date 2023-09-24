package com.demo.demostramingapp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.demo.demostramingapp.model.JsonResponse
import com.google.gson.Gson
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import javax.inject.Inject

/** Repository used to do provide the data to the ViewModel. */
class MyRepository @Inject constructor(private val context: Context) {

    private val dataset1LiveData = MutableLiveData<List<JsonResponse>>()
    private val dataset2LiveData = MutableLiveData<List<JsonResponse>>()
    private val dataset3LiveData = MutableLiveData<List<JsonResponse>>()
    private val allDataLoaded = CompletableDeferred<Unit>()
    private lateinit var items1: List<JsonResponse>
    private lateinit var items2: List<JsonResponse>
    private lateinit var items3: List<JsonResponse>

    val combinedData: LiveData<List<JsonResponse>> = MediatorLiveData<List<JsonResponse>>().apply {
        addSource(dataset1LiveData) { value = combineDatasets() }
        addSource(dataset2LiveData) { value = combineDatasets() }
        addSource(dataset3LiveData) { value = combineDatasets() }
    }

    /** Method used to fetch and post the dataset value. */
    suspend fun loadAllDatasets(
        jsonData1: String,
        jsonData2: String,
        jsonData3: String
    ) {

        coroutineScope {

            val job1 = launch {
                // Parse data 1
                items1 = parseJsonData(jsonData1)
            }

            val job2 = launch {
                // Parse data 2
                items2 = parseJsonData(jsonData2)
            }

            val job3 = launch {
                // Parse data 3
                items3 = parseJsonData(jsonData3)
            }

            // Wait for all coroutines to complete
            joinAll(job1, job2, job3)
            // Load and parse JSON datasets


            // Post the loaded data to LiveData
            dataset1LiveData.postValue(items1)
            dataset2LiveData.postValue(items2)
            dataset3LiveData.postValue(items3)

            // Signal that all datasets have been loaded
            allDataLoaded.complete(Unit)
        }
    }

    /** Method used to combine the all the dataset value. */
    private fun combineDatasets(): List<JsonResponse> {
        // Combine data from the three datasets here
        val combinedData = mutableListOf<JsonResponse>()
        combinedData.addAll(dataset1LiveData.value.orEmpty())
        combinedData.addAll(dataset2LiveData.value.orEmpty())
        combinedData.addAll(dataset3LiveData.value.orEmpty())
        return combinedData
    }

    /** Method used to fetch local JSON data. */
    private fun parseJsonData(jsonData: String): List<JsonResponse> {
        val json = context.assets.open(jsonData).bufferedReader().use {
            it.readText()
        }
        return listOf(Gson().fromJson(json, JsonResponse::class.java))
    }

    suspend fun awaitAllDataLoaded() {
        allDataLoaded.await()
    }

}
