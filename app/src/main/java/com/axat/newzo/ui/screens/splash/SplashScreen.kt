package com.axat.newzo.ui.screens.splash

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.axat.newzo.ui.navigation.NavigationRoute


@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {


    val color = remember { Animatable(Color.White) }

    LaunchedEffect(Unit) {
        color.animateTo(
            targetValue = Color.Red.copy(alpha = 0.6f),
            animationSpec = tween(1000),
        ).apply {
            navController.navigate(NavigationRoute.DASHBOARD_SCREEN)
        }
    }

    Scaffold(modifier = modifier.fillMaxSize()) { padding ->
        Column(
            modifier = modifier.fillMaxSize().background(color.value).padding(padding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://raw.githubusercontent.com/akshatbhuhagal/MrNewsMan/master/app/src/main/res/drawable/newsapplogo.png")
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.clip(CircleShape).size(100.dp)
            )

        }
    }


}