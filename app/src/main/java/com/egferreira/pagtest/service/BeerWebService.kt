package com.egferreira.pagtest.service

import com.egferreira.pagtest.beers.response.BeerResponse
import retrofit2.http.GET

interface BeerWebService {

    @GET("beers")
    suspend fun fetchBeerList(): List<BeerResponse>

}