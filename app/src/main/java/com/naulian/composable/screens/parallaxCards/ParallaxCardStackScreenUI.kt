package com.naulian.composable.screens.parallaxCards


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.naulian.composable.component.CodeBlock
import com.naulian.composable.theme.ComposeTheme
import com.naulian.modify.ExperimentalModifyApi
import com.naulian.modify.HugeIcons

@OptIn(ExperimentalMaterial3Api::class, ExperimentalModifyApi::class)
@Composable
fun ParallaxCardStackScreenUI(onBack: () -> Unit = {}) {
    val code = remember { parallaxCardStackCode }
    val scrollState = rememberLazyListState()

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            painter = painterResource(HugeIcons.Back),
                            contentDescription = "Back icon"
                        )
                    }
                },
                title = { Text(text = "Parallax Card Stack") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { scaffoldPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(scaffoldPadding),
            state = scrollState,
            verticalArrangement = Arrangement.spacedBy((-52).dp),
            contentPadding = PaddingValues(20.dp)
        ) {

            ParallaxCardStack(cardItems = dummyCards, state = scrollState)

            item {
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
private fun ParallaxCardStackScreenUIPreview() {
    ComposeTheme {
        ParallaxCardStackScreenUI()
    }
}
