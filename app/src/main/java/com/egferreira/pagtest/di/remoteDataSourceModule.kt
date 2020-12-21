package com.egferreira.pagtest.di

import com.egferreira.pagtest.service.BeerWebService
import com.egferreira.pagtest.service.WebServiceFactory
import org.koin.dsl.module

val remoteDataSourceModule = module {

    factory<BeerWebService> {
        WebServiceFactory.createService(
            WebServiceFactory.provideOkHttpClient()
        )
    }
}