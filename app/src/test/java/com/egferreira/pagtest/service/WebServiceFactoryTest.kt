package com.egferreira.pagtest.service

import com.egferreira.pagtest.base.Constants
import io.mockk.*
import okhttp3.OkHttpClient
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Suppress("ClassName")
class WebServiceFactoryTest {
    private val client: OkHttpClient = mockk()
    private val subject = WebServiceFactory
    private val retrofitBuilder: Retrofit.Builder = mockk()
    private val gsonConverterFactory: GsonConverterFactory = mockk()
    private val retrofit: Retrofit = mockk()

    @BeforeEach
    fun setup() {
        stubRetrofitBuilder()
        stubGsonConverterFactory()
    }

    private fun stubRetrofitBuilder() {
        mockkConstructor(Retrofit.Builder::class)
        every { anyConstructed<Retrofit.Builder>().client(client) } returns retrofitBuilder
        every {
            retrofitBuilder.baseUrl(Constants.BASE_URL)
        } returns retrofitBuilder
        every { retrofitBuilder.addConverterFactory(any()) } returns retrofitBuilder
        every { retrofitBuilder.build() } returns retrofit
        every { retrofit.create(BeerWebService::class.java) } returns mockk()
    }

    private fun stubGsonConverterFactory() {
        mockkStatic(GsonConverterFactory::class)
        every { GsonConverterFactory.create(any()) } returns gsonConverterFactory
    }


    @Nested
    inner class `WHEN call defaultRetrofitBuilder` {

        @Test
        fun `MUST call in order`() {
            subject.defaultRetrofitBuilder(client)
            verifyOrder {
                anyConstructed<Retrofit.Builder>().client(client)
                retrofitBuilder.baseUrl(Constants.BASE_URL)
                retrofitBuilder.addConverterFactory(gsonConverterFactory)
                retrofitBuilder.build()
            }
        }
    }

    @Nested
    inner class `WHEN call createService`{

        @Test
        fun `WITH serviceClass in argument MUST call retrofit create`(){
            val serviceClass = BeerWebService::class.java
            subject.createService(serviceClass, client)
            verify {
                retrofit.create(serviceClass)
            }
        }

        @Test
        fun `WITH client only in arguments of method MUST call retrofit create`(){
            subject.createService<BeerWebService>(client)
            verify {
                retrofit.create(BeerWebService::class.java)
            }
        }
    }

}