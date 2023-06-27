package com.hydok.composeanimation.paging.network

import com.hydok.composeanimation.paging.Photo
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("list")
    suspend fun getPhotos(
        @Query("page") pageNumber: Int,
        @Query("limit") limit: Int = 20
    ): List<Photo>
}