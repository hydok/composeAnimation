package com.hydok.composeanimation.paging

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun PagingScreen(viewModel: PhotoViewModel = viewModel()) {
    val photos = viewModel.getPhotoPagination().collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(15.dp),
        contentPadding = PaddingValues(20.dp)
    ) {


        items(photos.itemCount) {
            photoItem(photos[it]!!)
        }

        photos.apply {
            when {
                loadState.refresh is LoadState.Loading -> item {
                    Box(Modifier.fillParentMaxSize()) {
                        CircularProgressIndicator(
                            Modifier
                                .width(50.dp)
                                .align(Alignment.Center)
                        )
                    }
                }

                loadState.append is LoadState.Loading -> item {
                    Box(Modifier.fillMaxWidth()) {
                        CircularProgressIndicator(
                            Modifier
                                .padding(top = 20.dp)
                                .width(40.dp)
                                .align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun photoItem(photo: Photo) {

    Card(Modifier.fillMaxWidth(), shape = RoundedCornerShape(10.dp), elevation = 3.dp) {
        Row {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(photo.downloadUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(100.dp)
                    .width(200.dp)
            )
            /*SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(photo.downloadUrl)
                    .crossfade(true)
                    .build(), contentDescription = null,
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
            ) {
                val state = painter.state
                if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                    CircularProgressIndicator()
                } else {
                    SubcomposeAsyncImageContent()
                }
            }*/


            Text(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 10.dp, end = 10.dp), text = photo.author
            )

        }
    }
}
