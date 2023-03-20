package com.example.submission1

import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @Headers("Authorization: token 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")
    @GET("search/users")
    fun getGithub(@Query("q") q: String
    ): Call<GithubResponse>


}