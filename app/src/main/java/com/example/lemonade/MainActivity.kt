package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.lemonade.ui.theme.LemonadeTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val (step, setStep) = remember {
                mutableStateOf(1)
            }

            val imageResource = when(step) {
                1 -> R.drawable.lemon_tree
                2 -> R.drawable.lemon_squeeze
                3 -> R.drawable.lemon_drink
                else -> R.drawable.lemon_restart
            }

            val imageDescriptionContent = when (step) {
                1 -> R.string.img_1
                2 -> R.string.img_2
                3 -> R.string.img_3
                else -> R.string.img_4
            }

            val description = when (step) {
                1 -> R.string.str_1
                2 -> R.string.str_2
                3 -> R.string.str_3
                else -> R.string.str_4
            }

            LemonadeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column (
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = innerPadding.calculateTopPadding())
                            .background(Color.White)
                    ) {
                        ImageWithText(
                            imageResource = imageResource,
                            imageDescriptionContent = imageDescriptionContent,
                            description = description,
                            onClick = {
                                if (step < 4) {
                                    setStep(step + 1)
                                } else {
                                    setStep(1)
                                }
                            }
                        )

                    }
                }
            }
        }
    }
}

@Composable
fun ImageWithText(
    imageResource: Int,
    imageDescriptionContent: Int,
    description: Int,
    onClick: () -> Unit
) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Yellow)
            .padding(
                top = 10.dp,
                bottom = 10.dp,
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(R.string.app_name),
            color = Color.Black,
        )
    }

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        Box (
            modifier = Modifier
                .background(
                    color = Color.LightGray,
                    shape = RoundedCornerShape(30.dp)
                )
                .padding(bottom = 26.dp)
                .size(300.dp)
                .clickable(
                    enabled = true,
                    onClick = onClick
                ),
            contentAlignment = Alignment.Center
        ) {
            Image (
                painter = painterResource(imageResource),
                contentDescription = stringResource(imageDescriptionContent),
            )
        }

        Text (
            text = stringResource(description),
            fontSize = 20.sp,
            color = Color.Black,
            modifier = Modifier.padding(top = 46.dp)
        )
    }
}