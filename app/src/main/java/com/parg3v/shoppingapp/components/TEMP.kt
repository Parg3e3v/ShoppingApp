package com.parg3v.shoppingapp.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.with
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.parg3v.shoppingapp.R

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TEMP() {

    var count by remember { mutableStateOf(0) }

    IconButton(onClick = { count-- }) {
        Icon(
            painter = painterResource(id = R.drawable.icon_subtract),
            contentDescription = "subtract"
        )
    }

    Box(
        modifier = Modifier
            .border(BorderStroke(1.dp, Color.Gray), RoundedCornerShape(17.dp))
            .padding(vertical = 10.dp, horizontal = 15.dp),
        contentAlignment = Alignment.Center
    ) {
        AnimatedContent(
            targetState = count,
            transitionSpec = {
                slideIntoContainer(
                    towards = if (targetState > initialState) AnimatedContentScope.SlideDirection.Up else AnimatedContentScope.SlideDirection.Down,
                    animationSpec = tween(durationMillis = 500)
                ) with ExitTransition.None
            }, label = ""
        ) { targetCount ->
            Text(
                fontSize = 18.sp,
                text = targetCount.toString(),
                textAlign = TextAlign.Center
            )
        }
    }

    IconButton(onClick = { count++ }) {
        Icon(
            painter = painterResource(id = R.drawable.icon_add),
            contentDescription = "add"
        )
    }
}