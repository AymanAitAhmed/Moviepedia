package com.example.moviepedia.presentation.movie_list_template

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.moviepedia.R
import com.example.moviepedia.components.Constants


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SmallMovieCard(
    imageURL: String,
    title: String,
    language: String,
    rating: Float,
    ratingCount: Int,
    onClick : () -> Unit
) {

    val lottieComposition = rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.animation_lnnc2so3))

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        shape = RectangleShape,
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        onClick = onClick
    ) {

        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("${Constants.IMAGE_BASE_URL}$imageURL")
                .crossfade(1000)
                .build(),
            contentDescription = "image",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f),
            contentScale = ContentScale.Crop,
            loading = {
                LottieAnimation(
                    composition = lottieComposition.value,
                    iterations = LottieConstants.IterateForever
                )
            },
            error = {
                Icon(painter = painterResource(
                    id = R.drawable.baseline_image_not_supported_24),
                    contentDescription = "poster error",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(4.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_language_24),
                    contentDescription = "language",
                    modifier = Modifier
                        .fillMaxHeight(0.7f),
                    tint = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = language,
                    fontSize = 10.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    painter = painterResource(id = R.drawable.baseline_star_24),
                    contentDescription = "rating",
                    modifier = Modifier
                        .fillMaxHeight(0.7f),
                    tint = Color(0xFFf5bf03)
                )
                Text(
                    text = "$rating($ratingCount)",
                    fontSize = 10.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
        }
    }

}