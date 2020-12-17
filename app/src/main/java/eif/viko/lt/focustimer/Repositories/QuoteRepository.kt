package eif.viko.lt.focustimer.Repositories

import eif.viko.lt.focustimer.api.MotivationalQuotesAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object QuoteRepository {
    const val BASE_URL = "https://type.fit/api/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api: MotivationalQuotesAPI by lazy { retrofit.create(MotivationalQuotesAPI::class.java) }
}