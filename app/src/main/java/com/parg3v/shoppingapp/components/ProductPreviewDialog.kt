package com.parg3v.shoppingapp.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
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
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.parg3v.domain.model.Product
import com.parg3v.shoppingapp.R


@Composable
fun MinimalDialog(item: Product, onDismissRequest: () -> Unit) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        PreviewCard()
    }
}

@Preview
@Composable
fun PreviewCard() {
    val placeholder = painterResource(id = R.drawable.placeholder)
    Card {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data("").crossfade(true).build(),
                error = placeholder,
                placeholder = placeholder,
                contentDescription = "Title",
                contentScale = ContentScale.Fit,
                alignment = Alignment.Center,
                modifier = Modifier
                    .clip(RoundedCornerShape(dimensionResource(id = R.dimen.card_image_border_radius)))
                    .fillMaxWidth()
                    .fillMaxHeight(0.4F)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Title",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.favourite_icon),
                        contentDescription = "Add to favourites"
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_subtract),
                    contentDescription = "subtract"
                )

                Text(
                    modifier = Modifier
                        .border(BorderStroke(1.dp, Color.Gray), RoundedCornerShape(17.dp))
                        .padding(vertical = 13.dp, horizontal = 20.dp)
                        .wrapContentHeight(),
                    fontSize = 18.sp,
                    text = "1",
                    textAlign = TextAlign.Center
                )

                Icon(
                    painter = painterResource(id = R.drawable.icon_add),
                    contentDescription = "add"
                )

                Spacer(modifier = Modifier.weight(1F))

                Text(
                    text = "$50.99",
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



        }
    }
}