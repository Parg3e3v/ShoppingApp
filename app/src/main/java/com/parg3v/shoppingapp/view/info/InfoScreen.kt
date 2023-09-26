package com.parg3v.shoppingapp.view.info

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.parg3v.domain.model.Product
import com.parg3v.domain.model.Rating
import com.parg3v.shoppingapp.R

@Composable
fun InfoScreen(item: Product) {
    Box(modifier = Modifier.fillMaxSize()) {
        ProductDetailsUI(item = item)
    }
}


@Composable
fun ProductDetailsUI(item: Product) {
    val placeholder = painterResource(id = R.drawable.placeholder)

    Column(
        modifier = Modifier
            .padding(20.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(item.image)
                .crossfade(true)
                .build(),
            error = placeholder,
            placeholder = placeholder,
            contentDescription = item.title,
            contentScale = ContentScale.Fit,
            alignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(dimensionResource(id = R.dimen.card_image_border_radius)))
                .fillMaxWidth()
                .fillMaxHeight(0.4F)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = item.title,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.weight(6F)
            )
            IconButton(onClick = { /*TODO*/ }, modifier = Modifier.weight(1F)) {
                Icon(
                    painter = painterResource(id = R.drawable.favourite_icon),
                    contentDescription = "Add to favourites"
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                modifier = Modifier.weight(2F),
                text = "${item.rating.rate}",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )

            Text(
                modifier = Modifier.weight(1F),
                text = "$${item.price}",
                textAlign = TextAlign.End,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth(0.9F)
                .height(1.dp)
                .background(Color.Gray)
        )

        Text(text = "Product Details", modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = item.description,
            modifier = Modifier.fillMaxWidth()
        )

    }
}

@Preview
@Composable
fun Preview() {
    InfoScreen(
        item = Product(
            1,
            "Title",
            50.99F,
            "Easy upgrade for faster boot up, shutdown, application load and response (As compared to 5400 RPM SATA 2.5” hard drive; Based on published specifications and internal benchmarking tests using PCMark vantage scores) Boosts burst write performance, making it ideal for typical PC workloads The perfect balance of performance and reliability Read/write speeds of up to 535MB/s/450MB/s (Based on internal testing; Performance may vary depending upon drive capacity, host device, OS and application.)",
            "",
            "",
            Rating(4.5F, 519)
        )
    )
}