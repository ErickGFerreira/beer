package com.egferreira.pagtest.di

import com.egferreira.pagtest.service.BeerRepository
import com.egferreira.pagtest.service.BeerRepositoryImpl
import com.google.gson.Gson
import org.koin.dsl.module

val repositoryModule = module {

    factory <BeerRepository> {
        BeerRepositoryImpl(beerWebService = get())
    }

}