package com.naulian.composable.screens.icc

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.naulian.composable.component.ListItemText
import com.naulian.modify.ExperimentalModifyApi
import com.naulian.modify.HugeIcons
import com.naulian.modify.columnItem
import com.naulian.neumorphic.NeumorphicDownHorizontalDivider

sealed interface IccUIEvent {
    data object Back : IccUIEvent
    data object RatingStars : IccUIEvent
    data object ParallaxCardStack : IccUIEvent
    data object CarouselCard : IccUIEvent
    data object Progress : IccUIEvent
    data object BottomBar : IccUIEvent
    data object CalenderTopBar : IccUIEvent
    data object AnimatedInteractionScreen : IccUIEvent
    data object GlassDashboardScreen : IccUIEvent
    data object Cards : IccUIEvent
    data object Shapes : IccUIEvent
}

@OptIn(ExperimentalModifyApi::class, ExperimentalMaterial3Api::class)
@Composable
fun InteractiveCCScreenUI(
    uiEvent: (IccUIEvent) -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { uiEvent(IccUIEvent.Back) }) {
                        Icon(
                            painter = painterResource(HugeIcons.Back),
                            contentDescription = "Back Icon"
                        )
                    }
                },
                title = { Text(text = "Interactive Components") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { scaffoldPadding ->
        LazyColumn(
            modifier = Modifier.padding(scaffoldPadding)
        ) {
            columnItem {
                NeumorphicDownHorizontalDivider()
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { uiEvent(IccUIEvent.RatingStars) }
                        .padding(20.dp)
                ) {
                    ListItemText(title = "Rating Stars", contributor = "Naulian")
                }
                NeumorphicDownHorizontalDivider()

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { uiEvent(IccUIEvent.ParallaxCardStack) }
                        .padding(20.dp)
                ) {
                    ListItemText(title = "Parallax Card Stack", contributor = "Aryan Jaiswal")
                }
                NeumorphicDownHorizontalDivider()

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { uiEvent(IccUIEvent.CarouselCard) }
                        .padding(20.dp)
                ) {
                    ListItemText(title = "Corousel Card", contributor = "Aryan Jaiswal")
                }
                NeumorphicDownHorizontalDivider()

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { uiEvent(IccUIEvent.Progress) }
                        .padding(20.dp)
                ) {
                    ListItemText(title = "Progress Steps", contributor = "Aryan Singh")
                }
                NeumorphicDownHorizontalDivider()

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { uiEvent(IccUIEvent.BottomBar) }
                        .padding(20.dp)
                ) {
                    ListItemText(title = "Neumorphic Bottom Bar", contributor = "Zain ul Abdin")
                }
                NeumorphicDownHorizontalDivider()

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { uiEvent(IccUIEvent.CalenderTopBar) }
                        .padding(20.dp)
                ) {
                    ListItemText(title = "Calender Top Bar", contributor = "Zain ul Abdin ")
                }
                NeumorphicDownHorizontalDivider()

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { uiEvent(IccUIEvent.AnimatedInteractionScreen) }
                        .padding(20.dp)
                ) {
                    ListItemText(title = "Animated Interactions", contributor = "Shree Bhargav R K")
                }
                NeumorphicDownHorizontalDivider()

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { uiEvent(IccUIEvent.GlassDashboardScreen) }
                        .padding(20.dp)
                ) {
                    ListItemText(title = "Glass Card", contributor = "Shree Bhargav R K")
                }
                NeumorphicDownHorizontalDivider()

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { uiEvent(IccUIEvent.Cards) }
                        .padding(20.dp)
                ) {
                    ListItemText(title = "Cards", contributor = "Aryan Jaiswal")
                }
                NeumorphicDownHorizontalDivider()

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { uiEvent(IccUIEvent.Shapes) }
                        .padding(20.dp)
                ) {
                    ListItemText(title = "Shapes", contributor = "Prashant Panwar")
                }
                NeumorphicDownHorizontalDivider()
            }
        }
    }
}