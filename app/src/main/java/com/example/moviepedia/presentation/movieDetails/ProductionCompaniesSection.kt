package com.example.moviepedia.presentation.movieDetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.example.moviepedia.data.remote.movieDetails.ProductionCompany

@Composable
fun ProductionCompaniesSection(
    productionCompaniesList: List<ProductionCompany>
) {

    Text(
        text = "Production Companies:",
        fontSize = 15.sp,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier
            .fillMaxWidth(),
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Start,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1
    )

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        items(productionCompaniesList.size, key = {
            productionCompaniesList[it].id
        }){
            ProductionCompanyCard(productionCompany = productionCompaniesList[it])
        }
    }

}

@Composable
fun ProductionCompanyCard(
    productionCompany: ProductionCompany
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        SubcomposeAsyncImage(
            model = "https://image.tmdb.org/t/p/w500${ productionCompany.logo_path }",
            contentDescription = "poster",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
            loading = {
                CircularProgressIndicator()
            },
            colorFilter = ColorFilter.tint(color = Color.White, blendMode = BlendMode.Overlay)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "${ productionCompany.name }(${productionCompany.origin_country})",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold
        )
    }
}