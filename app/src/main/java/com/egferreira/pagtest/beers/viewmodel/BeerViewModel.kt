package com.egferreira.pagtest.beers.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.egferreira.pagtest.base.livedata.SingleLiveEvent
import com.egferreira.pagtest.base.response.Error
import com.egferreira.pagtest.beers.response.BeerResponse
import com.egferreira.pagtest.service.BeerRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BeerViewModel(
    val beerRepository: BeerRepository,
    val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    private val _beersLiveData = MutableLiveData<List<BeerResponse>>()
    private val _errorLiveData = SingleLiveEvent<Error>()
    private val _loadingLiveData = MutableLiveData<Boolean>()


    val beersLiveData: LiveData<List<BeerResponse>> = _beersLiveData
    val errorLiveData: LiveData<Error> = _errorLiveData
    val loadingLiveData: LiveData<Boolean> = _loadingLiveData

    init {
        fetchBeerList()
    }

    fun fetchBeerList() {
        viewModelScope.launch(dispatcher) {

            loadingBehavior(true)
            beerRepository.fetchBeerList()
                .onSuccess { beerList ->
                    _beersLiveData.value = beerList
                }
                .onFailure { error ->
                    _errorLiveData.value = error
                }
                .onAny {
                    loadingBehavior(false)
                }
        }
    }

    private fun loadingBehavior(isLoading: Boolean) {
        _loadingLiveData.value = isLoading
    }
}