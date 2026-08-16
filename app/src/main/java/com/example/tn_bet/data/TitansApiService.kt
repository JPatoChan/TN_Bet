package com.example.tn_bet.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

interface TitansApiService {
    @GET("apis/site/v2/sports/football/nfl/teams/{teamId}")
    suspend fun getTeamInfo(@Path("teamId") teamId: String = "10"): TeamResponse
}

object RetrofitClient {
    private const val BASE_URL = "https://site.api.espn.com/"

    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    val titansApi: TitansApiService by lazy {
        retrofit.create(TitansApiService::class.java)
    }
}
