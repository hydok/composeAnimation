package com.hydok.composeanimation.snow

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.IntSize
import androidx.core.util.toClosedRange
import androidx.core.util.toRange
import com.hydok.composeanimation.util.floatRandom
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random
import kotlin.random.nextInt

class Snow(private val size: Float, position: Offset, val screenSize: IntSize, angle: Float) {

    private var position: Offset by mutableStateOf(position)
    private var angle by mutableStateOf(angle)

    fun draw(drawScope: DrawScope) {
        drawScope.drawCircle(color = Color.White, radius = size, center = position)
    }

    fun update() {
        //drop speed
        val increment = floatRandom(2f, 2.5f)

        val xAngle = (increment * cos((angle)))
        val yAngle = (increment * sin((angle)))

        position = if (position.y > screenSize.height) position.copy(y = 0f)
        else position.copy(x = position.x + xAngle, y = position.y + yAngle)

        if (position.x > screenSize.width + 20) position = position.copy(x = 0f)
        else if ((position.x < -20)) position = position.copy(x = screenSize.width.toFloat())
    }
}