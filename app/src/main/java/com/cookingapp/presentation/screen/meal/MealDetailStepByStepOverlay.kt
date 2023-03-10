package com.cookingapp.presentation.screen.meal
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.cookingapp.R
import com.cookingapp.presentation.component.*
import androidx.compose.foundation.Image
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.draw.scale

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import java.lang.Integer.max
import java.lang.Integer.min

//debug
val exUrl = listOf<String>(
    "https://i2.wp.com/www.downshiftology.com/wp-content/uploads/2022/10/Roast-Chicken-main.jpg",
    "https://juliasalbum.com/wp-content/uploads/2015/10/22365066706_62265f0cd9_c-1.jpg",
    "https://bellyfull.net/wp-content/uploads/2022/11/Roasted-Turkey-blog-1.jpg",
    "https://s3.us-east-1.amazonaws.com/assets.mapleleaffarms.com/content/_1200x545_crop_center_85_none/Turducken.jpg"
)

val exTxt = listOf<String>(
    "1. Season the chicken inside and out.\n" +
            "2. Add the butter to the chicken and the pan.\n" +
            "3. Stuff the cavity with celery.\n" +
            "4. Bake until the chicken is fully roasted.",
    "prepare da duck",
    "prepare da turkey",
    "Turducken!"
)

data class Page(val imgUrl: String, val text: String)

@Composable
fun Overlay(navController: NavHostController) {
    var pageIndex by remember { mutableStateOf(0) } // state variable for page index
    val pages = listOf( // list of pages containing image and text
        Page(exUrl[0], exTxt[0]),
        Page(exUrl[1], exTxt[1]),
        Page(exUrl[2], exTxt[2]),
        Page(exUrl[3], exTxt[3]),
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f))
    ) {
        Box(
            modifier = Modifier
                .padding(32.dp)
                .background(Color.White)
                .fillMaxWidth()
                .fillMaxHeight()
                .align(Alignment.Center)
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 64.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = rememberAsyncImagePainter(pages[pageIndex].imgUrl),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.0f)
                        .padding(top = 16.dp)
                        .padding(start = 16.dp, end = 16.dp),
                    contentScale = ContentScale.FillWidth // Scale the image to fit inside the container
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = pages[pageIndex].text,
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Medium),
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp),
                )
            }
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                IconButton(
                    onClick = { pageIndex = max(0, pageIndex - 1) },
                    enabled = pageIndex > 0,
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.Black
                    )
                }
                Spacer(modifier = Modifier.width(64.dp))
                IconButton(
                    onClick = { pageIndex = min(pages.size - 1, pageIndex + 1) },
                    enabled = pageIndex < pages.size - 1,
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Forward",
                        tint = Color.Black
                    )
                }
            }
        }

    }
}