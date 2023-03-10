package com.cookingapp.presentation.screen.meal
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.foundation.Image
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import coil.compose.rememberAsyncImagePainter
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
    "Preheat the oven to 450 degrees Fahrenheit. Take the duck out of the refrigerator 30 minutes before preparing it. Zygaityte's recipe also includes a side dish of roasted baby potatoes, shallots, fresh figs, and Bartlett pears. You'll be roasting them first and then reheating them later after the duck has roasted."+
"Wash and cut the baby potatoes in half. Peel the three shallots and cut them in half lengthwise. Cut three pears in half, and scoop out the cores with a spoon or cut them out with a paring knife. Cut the figs in half vertically from the stem. Brush a roasting pan with canola oil or other oil with a high smoking point, and arrange the potatoes, shallots, figs, and pears."+
"If you would like a more interesting presentation, spray a baking rack with cooking spray, and insert it into a baking sheet. Arrange the figs and pears on the rack. When roasted, the figs and pears will have grill marks left by the rack. Put the pan (or pan and tray) into the oven and roast 30 minutes.",
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
                .fillMaxSize()
                .align(Alignment.Center)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()) // make the column scrollable
            ) {
                Column(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(400.dp)
                            .padding(top = 16.dp)
                            .padding(start = 16.dp, end = 16.dp)
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(pages[pageIndex].imgUrl),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(16.dp))
                                .aspectRatio(1.0f),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = pages[pageIndex].text,
                        style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Medium),
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp),
                    )
                    Spacer(modifier = Modifier.height(80.dp))
                }
            }
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.Black.copy(alpha = 0.3f)),
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