package com.hydok.composeanimation.util

import java.util.*
import kotlin.random.nextInt

fun floatRandom(from:Float, to:Float):Float{
    return from + Random().nextFloat() * (to - from)
}


fun intRandom(from:Int, to:Int):Int{
    return kotlin.random.Random.nextInt(from..to)
}



