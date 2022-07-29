package com.emptyslon.forismatic.dataBase

import retrofit2.Call
import retrofit2.http.GET

interface QuoteApi {
    @GET("./random")

    fun getData(): Call<QuotesList>
}