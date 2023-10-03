package com.example.moviepedia.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun GenreTypeCard(
    borderWidth: Dp = 1.dp,
    color: Color = MaterialTheme.colorScheme.onSurface,
    title: String,
    fontWeight: FontWeight = FontWeight.Bold,
    fontSize: TextUnit = TextUnit.Unspecified
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(15.dp))
            .border(borderWidth, color, RoundedCornerShape(15.dp))
            .padding(4.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            color = color,
            fontWeight = fontWeight,
            fontSize = fontSize,
            modifier = Modifier.padding(start = 4.dp, end = 4.dp)
        )
    }
}