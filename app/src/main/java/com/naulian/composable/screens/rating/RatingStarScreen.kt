package com.naulian.composable.screens.rating

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.naulian.composable.R
import com.naulian.composable.component.CodeBlock
import com.naulian.composable.theme.ComposeTheme
import com.naulian.modify.ExperimentalModifyApi
import com.naulian.modify.columnItem

@OptIn(ExperimentalModifyApi::class)
@Composable
fun RatingStarsScreen() {
    val code = remember {
        """
            @Composable
            fun RatingStars(
                @IntRange(0, 5)
                rating: Int,
                onRatingChange: (Int) -> Unit,
                unRatedStarIcon: Painter,
                ratedStarIcon: Painter,
                modifier: Modifier = Modifier,
                iconSize: Dp = 40.dp,
                itemSpacing: Dp = 8.dp,
                animOverlapDelay: Long = 200L,
                animationSpec: AnimationSpec<Float> = spring(
                    dampingRatio = Spring.DampingRatioHighBouncy,
                    stiffness = Spring.StiffnessLow
                )
            ) {
            
                var animatedRating by remember { mutableIntStateOf(rating) }
                val boxesScale = List(5) {
                    remember { Animatable(if(rating <= it) 0.8f else 1f) }
                }
                var prevRating by remember { mutableIntStateOf(rating) }
            
            
                LaunchedEffect(rating) {
                    val isDecreased = rating < prevRating
                    val boxes = if (isDecreased) boxesScale.take(prevRating).drop(rating).reversed()
                    else boxesScale.drop(prevRating).take(rating - prevRating)
            
                    prevRating = rating
                    boxes.forEachIndexed { index, animatable ->
                        launch {
                            delay(index * animOverlapDelay)
                            animatedRating = if (isDecreased) animatedRating - 1
                            else animatedRating + 1
                            animatable.animateTo(
                                targetValue = if (isDecreased) 0.8f else 1f,
                                animationSpec = animationSpec
                            )
                        }
                    }
                }
            
                Row(
                    modifier = modifier,
                    horizontalArrangement = Arrangement.spacedBy(itemSpacing)
                ) {
                    repeat(5) { index ->
                        Icon(
                            modifier = Modifier
                                .size(iconSize)
                                .scale(boxesScale[index].value)
                                .noRippleClick {
                                    onRatingChange(index + 1)
                                },
                            painter = if (index < animatedRating) ratedStarIcon else unRatedStarIcon,
                            contentDescription = null,
                            tint = Color.Unspecified
                        )
                    }
                }
            }
        """.trimIndent()
    }
    Scaffold { scaffoldPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(scaffoldPadding)
                .padding(20.dp)
        ) {
            columnItem(
                verticalArrangement = Arrangement.spacedBy(48.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                var ratingValue by remember { mutableIntStateOf(2) }

                RatingStars(
                    modifier = Modifier.padding(20.dp),
                    rating = ratingValue,
                    ratedStarIcon = painterResource(R.drawable.ic_star_filled),
                    unRatedStarIcon = painterResource(R.drawable.ic_star_outlined),
                    onRatingChange = {
                        ratingValue = it
                    },
                    iconSize = 48.dp
                )

                CodeBlock(
                    source = code,
                    language = "kotlin"
                )
            }
        }
    }
}

@Preview
@Composable
private fun RatingStarsScreenPreview() {
    ComposeTheme {
        RatingStarsScreen()
    }
}