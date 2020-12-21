package com.egferreira.pagtest.service

import com.egferreira.pagtest.base.request.Result
import com.egferreira.pagtest.beers.response.BeerResponse

interface BeerRepository{

    suspend fun fetchBeerList(): Result<List<BeerResponse>>
}