package com.egferreira.pagtest.viewmodel

import com.egferreira.pagtest.beers.response.BeerResponse
import com.egferreira.pagtest.beers.viewmodel.BeerViewModel
import com.egferreira.pagtest.service.BeerRepository
import com.egferreira.pagtest.utils.InstantExecutorExtension
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.jupiter.api.extension.ExtendWith
import com.egferreira.pagtest.base.request.Result
import com.egferreira.pagtest.base.response.Error
import org.junit.jupiter.api.*

@ExtendWith(InstantExecutorExtension::class)
@ExperimentalCoroutinesApi
class BeerViewModelTest {

    private val testCoroutineScope = TestCoroutineDispatcher()

    private val beerRepository = mockk<BeerRepository>()
    private lateinit var viewModel: BeerViewModel

    private val result = mockk<List<BeerResponse>>(relaxed = true)
    private val error = mockk<Error>(relaxed = true)

    @BeforeEach
    fun setup() {
        viewModel = BeerViewModel(beerRepository, testCoroutineScope)
    }

    @AfterEach
    fun tearDownAfterEach() {
        clearAllMocks()
    }

    @Test
    fun `When calling fetchBeerList Should call fetchBeerList on Repository`() = runBlocking {
        coEvery { beerRepository.fetchBeerList() } returns Result.success(result)
        viewModel.fetchBeerList()

        coVerify {
            beerRepository.fetchBeerList()
        }
    }
        @Test
        fun `When calling fetchBeerList and receive Success, the successObserver must be filled with Data`() = runBlocking {
            coEvery { beerRepository.fetchBeerList() } returns Result.success(result)
            viewModel.fetchBeerList()
            Assertions.assertNotNull(viewModel.beersLiveData.value)
            Assertions.assertNull(viewModel.errorLiveData.value)
        }

        @Test
        fun `When calling getAnticipateValues and receive Error, the failureObserver must be filled with Error`() = runBlocking {
            coEvery { beerRepository.fetchBeerList() } returns Result.failure(error)
            viewModel.fetchBeerList()
            Assertions.assertNull(viewModel.beersLiveData.value)
            Assertions.assertNotNull(viewModel.errorLiveData.value)
        }





}