package com.egferreira.pagtest.di

import com.egferreira.pagtest.beers.viewmodel.BeerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel { BeerViewModel(get()) }
}