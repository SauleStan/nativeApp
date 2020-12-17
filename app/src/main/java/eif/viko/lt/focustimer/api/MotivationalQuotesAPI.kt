package eif.viko.lt.focustimer.api

import eif.viko.lt.focustimer.Models.Quote
import retrofit2.Call
import retrofit2.http.GET

const val BASE_URL = "https://type.fit/api/"

interface MotivationalQuotesAPI {

    @GET("quotes")
    fun getQuoteList(): Call<List<Quote>>
}