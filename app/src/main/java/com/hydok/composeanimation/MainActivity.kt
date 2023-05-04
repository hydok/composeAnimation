package com.hydok.composeanimation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hydok.composeanimation.snow.SnowScreen
import com.hydok.composeanimation.ui.theme.ComposeAnimationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeAnimationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "main") {
                        composable(route = "main") {
                            Column(
                                modifier = Modifier.fillMaxSize().padding(10.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                MyButton("SNOW") {
                                    navController.navigate("snow")
                                }
                            }
                        }
                        composable(route = "snow") {
                            SnowScreen()
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun MyButton(str: String, click: () -> Unit) {
    Button(
        onClick = click,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.fillMaxWidth(0.8f)
    ) {
        Text(text = str, color = Color.White)
    }
}
