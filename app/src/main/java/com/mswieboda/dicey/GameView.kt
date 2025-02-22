package com.mswieboda.dicey

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mswieboda.dicey.ui.theme.DiceyTheme

const val TOTAL_DICE = 5

@Composable
fun GameView(modifier: Modifier = Modifier) {
    val diceState = remember { mutableStateOf(listOf<Die>()) }

    Column(modifier = modifier) {
        Header(onRollClick = { rollDice(diceState) })
        DiceRowView(diceState.value)
    }
}

fun rollDice(diceState: MutableState<List<Die>>) {
    val dice = List(TOTAL_DICE) { Die.entries.random() }

    diceState.value = dice.shuffled()
}

@Composable
fun Header(onRollClick: () -> Unit) {
    Row {
        Column {
            Text(
                text = "Dicey!",
                modifier = Modifier.padding(8.dp)
            )
        }
        Column {
            Button(onClick = onRollClick) {
                Text(
                    text = "Roll",
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Composable
fun DiceRowView(dice: List<Die>) {
    Row(
        modifier = Modifier.padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        for (die in dice) {
            Column(
                modifier = Modifier
                    .background(color = Color(0xFFFFFFFF))
                    .weight(1f)
                    .border(
                        width = 1.dp,
                        brush = Brush.linearGradient(colors = listOf(Color.Black, Color.Black)),
                        shape = RoundedCornerShape(12.dp)
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DieView(die)
            }
        }
    }
}

@Composable
fun DieView(die: Die) {
    Box(
        modifier = Modifier.aspectRatio(ratio = 1.0f),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = die.painter(),
            contentDescription = die.displaySymbol,
            alignment = Alignment.Center,
            contentScale = ContentScale.Fit
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GameViewPreview() {
    DiceyTheme {
        Column {
            Header(onRollClick = {})
            DiceRowView(List(TOTAL_DICE) { Die.entries.random() })
        }
    }
}
