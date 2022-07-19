package com.emptyslon.forismatic.dataBase

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface QuoteApi {
    @GET("./random")

    fun getData(): Call<Quote>
}