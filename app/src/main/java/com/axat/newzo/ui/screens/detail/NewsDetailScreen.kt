package com.axat.newzo.ui.screens.detail

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.axat.newzo.R
import com.axat.newzo.utility.SharedViewModel
import com.axat.newzo.utility.formatDate


@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsDetailScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    sharedViewModel: SharedViewModel?
) {

    LaunchedEffect(Unit) {

    }


    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "News Detail", textAlign = TextAlign.Center, fontFamily = FontFamily(Font(R.font.valofont)),
                        overflow = TextOverflow.Ellipsis, color = Color.White)
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Red.copy(alpha = 0.6f)),
                navigationIcon = {
                    if (navController.previousBackStackEntry != null) {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.White
                            )
                        }
                    } else {
                        null
                    }
                }
            )
        },
        content = { paddingValue ->
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(paddingValue),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {

                AsyncImage(
                    ImageRequest.Builder(LocalContext.current)
                        .data(sharedViewModel?.sharedState?.value?.urlToImage ?: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQWTMaowVw4ABlJ5di42VwbCJuVVGay40W8otrBe_v-HA&s")
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    filterQuality = FilterQuality.High,
                    contentScale = ContentScale.FillHeight,
                    modifier = modifier
                        .clip(RoundedCornerShape(bottomEnd = 24.dp, bottomStart = 24.dp))
                        .height(250.dp)
                )

                Spacer(modifier = modifier.height(10.dp))

                Text(text = "${sharedViewModel?.sharedState?.value?.title}",
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                    style = TextStyle(fontSize = 16.sp, fontFamily = FontFamily(Font(R.font.montserrat_regular)), color = Color.Black, fontWeight = FontWeight.W700),
                    modifier = modifier.padding(5.dp).padding(horizontal = 10.dp)
                )

                Spacer(modifier = modifier.height(5.dp))

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start,
                    modifier = modifier.fillMaxWidth()
                ) {

                    Text(text = "Author: ${sharedViewModel?.sharedState?.value?.author}",
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyle(fontSize = 12.sp, fontFamily = FontFamily(Font(R.font.montserrat_regular)), color = Color.Black, fontWeight = FontWeight.Bold),
                        modifier = modifier.padding(5.dp).padding(horizontal = 10.dp)
                    )

                    Text(text = "${formatDate(sharedViewModel?.sharedState?.value?.publishedAt ?: "2024-02-14")}",
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyle(fontSize = 12.sp, fontFamily = FontFamily(Font(R.font.montserrat_regular)), color = Color.Black, fontWeight = FontWeight.Bold),
                        modifier = modifier.padding(5.dp).padding(horizontal = 10.dp)
                    )

                }

                Spacer(modifier = modifier.height(5.dp))

                Text(text = "${sharedViewModel?.sharedState?.value?.description}",
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(fontSize = 12.sp, fontFamily = FontFamily(Font(R.font.montserrat_regular)), color = Color.Black, fontWeight = FontWeight.W500),
                    modifier = modifier.padding(5.dp).padding(horizontal = 10.dp)
                )

                Spacer(modifier = modifier.height(5.dp))

                Text(text = "${sharedViewModel?.sharedState?.value?.content ?: ""} - ${sharedViewModel?.sharedState?.value?.source?.name}",
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(fontSize = 12.sp, fontFamily = FontFamily(Font(R.font.montserrat_regular)), color = Color.Black, fontWeight = FontWeight.W500),
                    modifier = modifier.padding(5.dp).padding(horizontal = 10.dp)
                )

            }
        }
    )


}