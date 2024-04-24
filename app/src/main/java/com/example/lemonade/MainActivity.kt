package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@Composable
fun LemonadeWithButtonAndImage(modifier: Modifier = Modifier) {
    var step by remember { mutableStateOf(1) }
    var randomSquare by remember { mutableStateOf(1) }
    var randomLimit by remember { mutableStateOf(1) }
    var isSueeze by remember { mutableStateOf(false) }

    var selectedSquare: () -> Unit = {
        println("Current step: $step")
        println("Current randomSquare: $randomSquare")
        println("Current randomLimit: $randomLimit")
        if (step == 2 && !isSueeze) {
            isSueeze = true
            randomSquare = (1..6).random()
            randomLimit = 1
        } else if (step == 2 && isSueeze) {
            if (randomLimit == randomSquare) {
                step++
                isSueeze = false
            }
            randomLimit++
        } else {
            step = (step % 4) + 1
        }
    }

    val imageResource = when (step) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        4 -> R.drawable.lemon_restart
        else -> R.drawable.lemon_restart
    }

    val textResource = when (step) {
        1 -> stringResource(R.string.one_step)
        2 -> stringResource(R.string.two_step)
        3 -> stringResource(R.string.three_step)
        4 -> stringResource(R.string.four_step)
        else -> stringResource(R.string.four_step)
    }
    Box(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Button(
                onClick = selectedSquare,
                shape = RoundedCornerShape(16),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
            ) {
                Image(
                    painter = painterResource(id = imageResource),
                    contentDescription = imageResource.toString(),
                    modifier = Modifier
                        .wrapContentSize(),
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = textResource,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.wrapContentSize()
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LemonadeApp() {
    LemonadeTheme {
        LemonadeWithButtonAndImage(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        )
    }
}