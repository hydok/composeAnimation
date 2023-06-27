package com.hydok.composeanimation.paging

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.hydok.composeanimation.paging.network.RetrofitBuilder
import kotlinx.coroutines.flow.Flow

class PhotoViewModel : ViewModel() {

    private val photoRepository: PhotoRepository = PhotoRepository(RetrofitBuilder.apiService)
    fun getPhotoPagination(): Flow<PagingData<Photo>> = photoRepository.getPhotos()
}