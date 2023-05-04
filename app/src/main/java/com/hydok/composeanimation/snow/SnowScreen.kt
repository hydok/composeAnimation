package com.hydok.composeanimation.snow

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import com.hydok.composeanimation.util.floatRandom
import kotlinx.coroutines.android.awaitFrame
import kotlinx.coroutines.isActive
import kotlin.math.PI
import kotlin.random.Random
import kotlin.random.nextInt


@Composable
fun SnowScreen() {
    val screenWidth = LocalDensity.current.run {
        (Dp(LocalConfiguration.current.screenWidthDp.toFloat())).toPx().toInt()
    }
    val screenHeight = LocalDensity.current.run {
        (Dp(LocalConfiguration.current.screenHeightDp.toFloat())).toPx().toInt()
    }
    val snowState by remember { mutableStateOf(createSnowList(IntSize(screenWidth, screenHeight))) }

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {

        snowState.forEach {
            it.draw(this)
        }

    }

    LaunchedEffect(Unit){
        while (isActive){
            awaitFrame()
            snowState.forEach {
                it.update()
            }
        }

    }
}


fun createSnowList(size: IntSize): List<Snow> {
    val angleSeed = 25f
    return List(400) {
        Snow(
            size = floatRandom(1f,20f),
            position = Offset(x = floatRandom(0f, size.width.toFloat()), y = floatRandom(0f,size.height.toFloat())),
            screenSize = size,
            angle =(floatRandom(10f,100f) / angleSeed * 0.1f + (PI.toFloat() / 2.0.toFloat()))
        )
    }
}
