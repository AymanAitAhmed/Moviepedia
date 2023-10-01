package com.example.moviepedia.presentation.movieDetails

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.example.moviepedia.R
import kotlinx.coroutines.launch

@Composable
fun OverviewSection(
    description : String
) {

    val maxLines = rememberSaveable {
        mutableStateOf(3)
    }
    val expandArrowRotation = remember{
        Animatable(0f)
    }
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(tween(800))
            .clickable {
                if (maxLines.value == 3) {
                    maxLines.value = Int.MAX_VALUE
                    scope.launch {
                        expandArrowRotation.animateTo(180f, tween(800))
                    }
                } else {
                    maxLines.value = 3
                    scope.launch {
                        expandArrowRotation.animateTo(0f)
                    }
                }
            },
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {

        Text(
            text = "Overview:",
            fontSize = 15.sp,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .fillMaxWidth(),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )

        Text(
            text = description,
            fontSize = 13.sp,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .fillMaxWidth(),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            overflow = TextOverflow.Ellipsis,
            maxLines = maxLines.value,

            )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.End
        ) {

            Icon(
                painter = painterResource(id = R.drawable.baseline_expand_more_24),
                contentDescription = "Expand",
                modifier = Modifier.rotate(expandArrowRotation.value))
        }
    }


}