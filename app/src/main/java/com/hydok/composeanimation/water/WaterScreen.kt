package com.hydok.composeanimation.water

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.sin


@Composable
fun WaterScreen() {
    /*Water()

    LaunchedEffect(Unit){

    }*/
    WaveAnimation()
}




@Composable
fun Water() {

    Canvas(modifier = Modifier.fillMaxSize()) {
        val path = Path()
        val amplitude = 200
        val waveProgress = 10

        for (x in 0..size.width.toInt()) {
            val y =
                size.height / 2 + amplitude * sin((2 * PI * (x + waveProgress * size.width)) / size.width).toFloat()

            if (x == 0) {
                path.moveTo(x.toFloat(), y)
            } else {
                path.lineTo(x.toFloat(), y)
            }
        }

        path.lineTo(size.width , size.height)
        path.lineTo(0f,size.height)

        drawPath(path = path,color = Color.Blue)
    }
}



@Composable
fun WaveAnimation() {
    var waveOffset by remember { mutableStateOf(0f) }

    LaunchedEffect(Unit) {
        while (true) {
            withFrameNanos { time ->
                waveOffset = (time / 1e9f).toFloat() * 100f
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF64B5F6))
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(10) { index ->
                WaveItem(waveOffset = waveOffset, index = index)
            }
        }
    }
}

@Composable
fun WaveItem(waveOffset: Float, index: Int) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {
        val width = size.width
        val height = size.height

        val amplitude = 20f
        val frequency = 0.03f
        val yOffset = amplitude * sin((waveOffset + index * 50f) * frequency)

        val wavePath = Path()
        val waveSegments = 100 // 웨이브 세그먼트 수
        val segmentWidth = width / waveSegments

        wavePath.moveTo(0f, height / 2 + yOffset)
        for (i in 0 until waveSegments) {
            val x = i * segmentWidth
            val y = (height / 2 + yOffset) + amplitude * sin((waveOffset + x + index * 50f) * frequency)
            wavePath.lineTo(x, y)
        }

        drawPath(path = wavePath, color = Color(0xFF2196F3))
    }
}








