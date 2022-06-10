package com.alancamargo.lystchallenge.features.doglist.data.api

import com.alancamargo.lystchallenge.features.doglist.data.model.DogResponse
import retrofit2.http.GET

interface DogService {

    @GET("/v1/breeds")
    suspend fun getDogs(): List<DogResponse>

}
