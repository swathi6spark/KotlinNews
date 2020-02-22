package com.swathi.kotlinnews.Retrofit

import retrofit2.Call
import retrofit2.http.GET

interface Api {

    @get:GET(".json")
    val kotlinFeed: Call<KotlinFeed>

    companion object {

        val BASE_URL = "https://www.reddit.com/r/kotlin/"
    }
}
