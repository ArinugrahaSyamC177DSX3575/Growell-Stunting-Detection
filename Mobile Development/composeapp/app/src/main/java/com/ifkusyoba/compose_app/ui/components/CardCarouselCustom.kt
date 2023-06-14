package com.ifkusyoba.compose_app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import com.ifkusyoba.compose_app.data.local.CardDataSource
import com.ifkusyoba.compose_app.ui.theme.ComposeAppTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CardCarouselCustom(
    modifier: Modifier = Modifier
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        val pagerState = rememberPagerState(initialPage = 0)
        val scope = rememberCoroutineScope()
        LaunchedEffect(pagerState) {
            scope.launch {
                repeat(Int.MAX_VALUE) {
                    pagerState.animateScrollToPage(
                        page = (pagerState.currentPage + 1) % pagerState.pageCount
                    )
                    delay(3000)
                }
            }
        }
        Column(modifier = Modifier) {
            HorizontalPager(
                state = pagerState,
                count = CardDataSource.cardData.size,
                modifier = Modifier.height(300.dp)
            ) { index ->
                val cardItem = CardDataSource.cardData[index]
                Card(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .height(280.dp)
                        .fillMaxWidth()
                        .graphicsLayer {
                            val pageOffset =
                                calculateCurrentOffsetForPage(index).absoluteValue
                            lerp(
                                start = 0.85f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            )
                                .also { scale ->
                                    scaleX = scale
                                    scaleY = scale
                                }
                            alpha = lerp(
                                start = 0.5f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            )
                        },
                    shape = RoundedCornerShape(10.dp),
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row {
                            Image(
                                painter = painterResource(cardItem.icon),
                                contentDescription = "Card Image",
                                modifier = Modifier
                                    .size(64.dp)
                                    .align(Alignment.CenterVertically)
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = stringResource(cardItem.title),
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.CenterVertically),
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = stringResource(cardItem.subtitle),
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Justify,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {

                repeat(CardDataSource.cardData.size) {
                    val color = if (pagerState.currentPage == it) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.onBackground
                    }
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .size(16.dp)
                            .clickable {
                                scope.launch {
                                    pagerState.animateScrollToPage(it)
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(16.dp)
                                .graphicsLayer {
                                    scaleX = if (pagerState.currentPage == it) 1.5f else 1f
                                    scaleY = if (pagerState.currentPage == it) 1.5f else 1f
                                }
                                .background(color = color, shape = RoundedCornerShape(32.dp))
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardCarouselCustomPreview() {
    ComposeAppTheme {
//        CardCarouselCustom()
    }
}