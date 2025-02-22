package com.mswieboda.dicey

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mswieboda.dicey.ui.theme.DiceyTheme

const val TOTAL_DICE = 5
val DICE = listOf(1, 2, 3, 4, 5, 6)

@Composable
fun GameView(modifier: Modifier = Modifier) {
    val diceState = remember { mutableStateOf(listOf<Int>()) }

    Column(modifier = modifier) {
        Header(onRollClick = { rollDice(diceState) })
        DiceRowView(diceState.value)
    }
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

fun rollDice(diceState: MutableState<List<Int>>) {
    val dice = List(TOTAL_DICE) { DICE.random() }

    diceState.value = dice.shuffled()
}

@Composable
fun DiceRowView(dice: List<Int>) {
    Row(
        modifier = Modifier.padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        for (i in dice) {
            Column(
                modifier = Modifier
                    .background(color = Color(0xFFFFFFFF))
                    .weight(0.5f)
                    .border(
                        width = 1.dp,
                        brush = Brush.linearGradient(colors = listOf(Color.Black, Color.Black)),
                        shape = RoundedCornerShape(12.dp)
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DiceView(i)
            }
        }
    }
}

@Composable
fun DiceView(dice: Int) {
    Box(
        modifier = Modifier.aspectRatio(ratio = 1.0f),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.pip_one),
            contentDescription = "one pip",
//            TODO: use string resource for this
//            contentDescription = stringResource(R.string.image_description)
//          TODO: get this size to work to scale the SVG, without pixelating
            modifier = Modifier
                .background(color = Color(0xFF00FF00))
                .scale(5f) // TODO: this is pixelating it, not using vectors
                .aspectRatio(1f)  // Maintain aspect ratio
        )
        Text(
            text = dice.toString(),
            fontSize = 16.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color(0xFFFF00FF)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GameViewPreview() {
    DiceyTheme {
        Column {
            Header(onRollClick = {})
            DiceRowView(listOf(3, 5, 1, 2, 6))
        }
    }
}
