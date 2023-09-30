package com.example.moviepedia.presentation.movie_list_template

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.example.moviepedia.R
import com.example.moviepedia.components.AutoResizeText
import com.example.moviepedia.components.FontSizeRange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LargeMovieCard(
    imageURL: String,
    title: String,
    date: String,
    language: String,
    rating: Float,
    ratingCount: Int,
    onClick: () -> Unit
) {

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp),
        shape = RectangleShape,
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            SubcomposeAsyncImage(
                model = imageURL,
                contentDescription = "image",
                modifier = Modifier
                    .fillMaxWidth(0.25f)
                    .fillMaxHeight(),
                contentScale = ContentScale.Crop,
                loading = {
                    CircularProgressIndicator()
                }
            )

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = title,
                    fontSize = 15.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )

                Text(
                    text = date,
                    fontSize = 10.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    textAlign = TextAlign.Start,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    items(5) {
                        OutlinedCard(
                            modifier = Modifier
                                .fillMaxHeight(0.9f)
                                .padding(0.dp),
                            shape = RoundedCornerShape(15.dp),
                            colors = CardDefaults.outlinedCardColors(
                                //containerColor = MaterialTheme.colorScheme.onSurface
                            )
                        ) {
                            Text(
                                text = "action$it",
                                color = MaterialTheme.colorScheme.onSurface,
                                fontSize = 10.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(4.dp)
                            )
                        }
                    }
                }

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
                        fontSize= 10.sp,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                }
            }
        }
    }

}