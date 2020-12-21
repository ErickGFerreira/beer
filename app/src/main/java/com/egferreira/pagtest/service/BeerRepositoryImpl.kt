package com.egferreira.pagtest.service

import com.egferreira.pagtest.base.request.Result
import com.egferreira.pagtest.base.retrofitWrapper
import com.egferreira.pagtest.beers.response.BeerResponse


class BeerRepositoryImpl(
    private val beerWebService: BeerWebService
) : BeerRepository {
    override suspend fun fetchBeerList(): Result<List<BeerResponse>> {
        return retrofitWrapper {
            beerWebService.fetchBeerList()
        }
    }

}