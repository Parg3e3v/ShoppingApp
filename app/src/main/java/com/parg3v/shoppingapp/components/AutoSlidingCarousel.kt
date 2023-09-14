package com.parg3v.shoppingapp.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import com.parg3v.shoppingapp.R
import com.parg3v.shoppingapp.ui.theme.CustomPurple
import com.tbuonomo.viewpagerdotsindicator.compose.DotsIndicator
import com.tbuonomo.viewpagerdotsindicator.compose.model.DotGraphic
import com.tbuonomo.viewpagerdotsindicator.compose.type.ShiftIndicatorType
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AutoSlidingCarousel(
    modifier: Modifier = Modifier,
    autoSlideDuration: Long = integerResource(id = R.integer.carousel_slide_duration).toLong(),
    pagerState: PagerState = remember { PagerState() },
    itemsCount: Int,
    itemContent: @Composable (index: Int) -> Unit,
) {
    var key by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = key) {
        launch {
            delay(autoSlideDuration)
            with(pagerState) {
                val target = if (currentPage < itemsCount - 1) currentPage + 1 else 0
                animateScrollToPage(page = target)
                key = !key
            }
        }
    }

    Box(
        modifier = modifier.fillMaxWidth(),
    ) {
        HorizontalPager(pageCount = itemsCount, state = pagerState) { page ->
            itemContent(page)
        }

        DotsIndicator(

            dotCount = itemsCount,
            type = ShiftIndicatorType(
                dotsGraphic = DotGraphic(
                    color = CustomPurple
                )
            ),
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(dimensionResource(id = R.dimen.carousel_dots_padding_bottom))
        )
    }
}