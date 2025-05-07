package com.hashinology.dictionarycompose.feature_dictionary.data.remote

import com.hashinology.dictionarycompose.feature_dictionary.data.remote.dto.WordInfoDto
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryApi {
    @GET("/api/v2/enteries/en/{word}")
    suspend fun getWord(
        @Path("word") word: String
    ): List<WordInfoDto>

    companion object{
        const val BASE_URL = "https://api.dictionaryapi.dev/"
    }
}