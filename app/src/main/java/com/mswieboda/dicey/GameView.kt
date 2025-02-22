package com.mswieboda.dicey

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mswieboda.dicey.ui.theme.DiceyTheme

const val TOTAL_DICE = 5
const val DEBUG = true

@Composable
fun GameView(modifier: Modifier = Modifier) {
    val diceState = remember { mutableStateOf(List(TOTAL_DICE) { Die.entries.random() }) }

    Column(modifier = modifier) {
        DiceRowView(diceState.value)
        BidActions({}, { rollDice(diceState) })
    }
}

fun rollDice(diceState: MutableState<List<Die>>) {
    val dice = List(TOTAL_DICE) { Die.entries.random() }

    diceState.value = dice.shuffled()
}

@Composable
fun DiceRowView(dice: List<Die>) {
    Row {
        Text(text = "Your dice:", color = mainTextIconColor())
    }
    Row(horizontalArrangement = Arrangement.spacedBy(space = 8.dp, alignment = Alignment.CenterHorizontally)) {
        for (die in dice) {
            Column(
                modifier = Modifier.weight(1f),
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
        modifier = Modifier
            .aspectRatio(ratio = 1.0f)
            .border(
                width = 1.dp,
                brush = Brush.linearGradient(colors = listOf(mainTextIconColor(), mainTextIconColor())),
                shape = RoundedCornerShape(12.dp)
            ),
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = die.painter(),
            contentDescription = die.displaySymbol,
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(color = mainTextIconColor())
        )
    }
}

@Composable
fun BidActions(onPlaceBidClick: () -> Unit, onReRollClick: () -> Unit) {
    Row {
        Text(text = "Your bid:", color = mainTextIconColor())
    }
    Row(
        horizontalArrangement = Arrangement.spacedBy(space = 8.dp, alignment = Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {}) {
                Text(text = "^")
            }
            Text(
                text = "1",
                fontSize = 96.sp,
                color = mainTextIconColor()
            )
            Button(onClick = {}) {
                Text(text = "v")
            }
        }
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "x",
                fontSize = 96.sp,
                color = mainTextIconColor()
            )
        }
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {}) {
                Text(text = "^")
            }
            DieView(Die.One)
            Button(onClick = {}) {
                Text(text = "v")
            }
        }
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(space = 8.dp, alignment = Alignment.CenterHorizontally)
    ) {
        Button(onClick = onPlaceBidClick) {
            Text(text = "Place Bid")
        }
        Button(onClick = onReRollClick) {
            Text(text = "Re-Roll")
        }
    }
}

@Composable
fun mainTextIconColor(): Color {
    return MaterialTheme.colorScheme.contentColorFor(MaterialTheme.colorScheme.background)
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun GameViewPreview() {
    DiceyTheme {
        Column {
            GameView()
        }
    }
}
