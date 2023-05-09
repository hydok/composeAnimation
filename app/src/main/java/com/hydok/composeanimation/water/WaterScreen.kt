package com.hydok.composeanimation.water

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import kotlin.math.PI
import kotlin.math.sin

@Composable
fun WaterScreen() {


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