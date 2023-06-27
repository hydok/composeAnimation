package com.hydok.composeanimation.paging


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.hydok.composeanimation.paging.network.ApiService
import kotlinx.coroutines.flow.Flow

class PhotoRepository(private val apiService: ApiService) {


    fun getPhotos() : Flow<PagingData<Photo>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { PhotoSource(apiService) })
            .flow
    }
}