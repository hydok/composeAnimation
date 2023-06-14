package com.hydok.composeanimation.colorchange

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ColorScreen(){

    val label = "color"
    val currentColor = remember{ MutableTransitionState(Color.Black) }
    val transition = updateTransition(transitionState = currentColor, label = label)

    val color by transition.animateColor(
        transitionSpec = { tween(1000) },
        label = label,
        targetValueByState = { it },
    )

    LaunchedEffect(key1 = currentColor.currentState){
        currentColor.targetState = when(currentColor.currentState){
            Color.Black -> Color.Red
            Color.Red -> Color.Green
            Color.Green -> Color.Gray
            Color.Gray -> Color.Yellow
            else -> Color.Black
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(color))
}