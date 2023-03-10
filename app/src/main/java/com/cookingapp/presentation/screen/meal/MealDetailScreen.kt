package com.cookingapp.presentation.screen.meal

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.cookingapp.R
import com.cookingapp.presentation.component.*
import com.cookingapp.presentation.theme.Grey20
import com.cookingapp.presentation.theme.Red40
import com.cookingapp.util.LOADING

@Composable
fun MealDetailScreen(
    viewModel: MealDetailViewModel,
    navController: NavHostController,
    onStepByStepClicked: (String) -> Unit
) {

    val mealDetailState by viewModel.mealDetailState
        .collectAsState()

    val mealTitleState by viewModel.mealTitle

    Column(modifier = Modifier.fillMaxSize()) {
        NavBar(
            title = stringResource(id = R.string.lbl_detail, mealTitleState),
            onBackPress = {
                navController.navigateUp()
            },
            onStepByStep = {
                navController.navigate("MealDetailStepByStepOverlay")
            }
        )

        Spacer(modifier = Modifier.height(height = 20.dp))

        StateHandler(
            state = mealDetailState,
            onLoading = {
                LoadingMealDetail(
                    onLoading = { viewModel.setMealTitle(title = LOADING) }
                )
            },
            onFailure = {
                WarningMessage(textId = R.string.txt_empty_result)
            }
        ) { resource ->
            resource.data?.let { mealDetail ->

                val screenHeight = LocalContext.current.resources.displayMetrics.heightPixels.dp /
                        LocalDensity.current.density

                viewModel.setMealTitle(title = mealDetail.title)

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(state = rememberScrollState())
                ) {

                    if (mealDetail.screenShots.isEmpty()) {
                        NetworkImage(
                            url = mealDetail.thumbnail,
                            crossFade = 1000,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .requiredHeight(height = screenHeight * 0.6f)
                                .padding(vertical = 8.dp, horizontal = 12.dp)
                                .align(alignment = Alignment.CenterHorizontally)
                                .clip(shape = MaterialTheme.shapes.medium),
                            onLoading = {
                                ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                                    val indicatorRef = createRef()
                                    CircularProgressIndicator(
                                        modifier = Modifier.constrainAs(indicatorRef) {
                                            top.linkTo(parent.top)
                                            bottom.linkTo(parent.bottom)
                                            start.linkTo(parent.start)
                                            end.linkTo(parent.end)
                                        }
                                    )
                                }
                            },
                            onError = {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_warning),
                                    contentDescription = "",
                                    tint = Red40
                                )
                            }
                        )
                    } else {
                        CarouselView(
                            urls = mealDetail.screenShots.map { it.image },
                            modifier = Modifier
                                .fillMaxWidth()
                                .requiredHeight(height = screenHeight * 0.6f)
                                .padding(vertical = 8.dp, horizontal = 12.dp)
                                .align(alignment = Alignment.CenterHorizontally),
                            shape = MaterialTheme.shapes.medium,
                            crossFade = 1000
                        )
                    }
                    Spacer(modifier = Modifier.height(height = 30.dp))
                    Column(modifier = Modifier.padding(horizontal = 5.dp)) {

                        Text(
                            text = stringResource(id = R.string.lbl_about, mealDetail.title),
                            style = MaterialTheme.typography.h2,
                            color = MaterialTheme.colors.onBackground,
                            modifier = Modifier.padding(vertical = 10.dp)
                        )
                        ExpandableText(
                            text = mealDetail.description,
                            style = MaterialTheme.typography.caption,
                            color = MaterialTheme.colors.onBackground,
                            modifier = Modifier.padding(vertical = 10.dp)
                        )
                        Spacer(modifier = Modifier.height(height = 30.dp))
                        Text(
                            text = stringResource(id = R.string.lbl_extra),
                            style = MaterialTheme.typography.h2,
                            color = MaterialTheme.colors.onBackground,
                            modifier = Modifier.padding(vertical = 10.dp)
                        )
                        ExtraInformationRow(
                            firstTitle = stringResource(id = R.string.lbl_title),
                            secondTitle = stringResource(id = R.string.lbl_developer),
                            textColor = MaterialTheme.colors.onSurface
                        )
                        ExtraInformationRow(
                            firstTitle = mealDetail.title,
                            secondTitle = mealDetail.developer,
                            textColor = MaterialTheme.colors.onBackground
                        )
                        Spacer(modifier = Modifier.height(height = 20.dp))
                        ExtraInformationRow(
                            firstTitle = stringResource(id = R.string.lbl_publisher),
                            secondTitle = stringResource(id = R.string.lbl_release_date),
                            textColor = MaterialTheme.colors.onSurface
                        )
                        ExtraInformationRow(
                            firstTitle = mealDetail.publisher,
                            secondTitle = mealDetail.releaseDate,
                            textColor = MaterialTheme.colors.onBackground
                        )
                        Spacer(modifier = Modifier.height(height = 20.dp))
                        ExtraInformationRow(
                            firstTitle = stringResource(id = R.string.lbl_genre),
                            secondTitle = stringResource(id = R.string.lbl_platform),
                            textColor = MaterialTheme.colors.onSurface
                        )
                        ExtraInformationRow(
                            firstTitle = mealDetail.genre,
                            secondTitle = mealDetail.platform,
                            textColor = MaterialTheme.colors.onBackground,
                            icon = {
                                Box(modifier = Modifier.padding(end = 5.dp)) {
                                    Rating(text = mealDetail.platform)
                                }
                            }
                        )
                        Spacer(modifier = Modifier.height(height = 30.dp))
                        mealDetail.minimumSystemRequirements?.let { minimumSystemRequirements ->
                            Text(
                                text = stringResource(id = R.string.lbl_msr),
                                style = MaterialTheme.typography.h2,
                                color = MaterialTheme.colors.onSurface
                            )
                            Spacer(modifier = Modifier.height(height = 20.dp))
                            Text(
                                text = stringResource(id = R.string.lbl_os),
                                style = MaterialTheme.typography.caption,
                                color = MaterialTheme.colors.onSurface
                            )
                            Text(
                                text = minimumSystemRequirements.os,
                                style = MaterialTheme.typography.caption,
                                color = MaterialTheme.colors.onBackground
                            )
                            Spacer(modifier = Modifier.height(height = 15.dp))
                            Text(
                                text = stringResource(id = R.string.lbl_memory),
                                style = MaterialTheme.typography.caption,
                                color = MaterialTheme.colors.onSurface
                            )
                            Text(
                                text = minimumSystemRequirements.memory,
                                style = MaterialTheme.typography.caption,
                                color = MaterialTheme.colors.onBackground
                            )
                            Spacer(modifier = Modifier.height(height = 15.dp))
                            Text(
                                text = stringResource(id = R.string.lbl_storage),
                                style = MaterialTheme.typography.caption,
                                color = MaterialTheme.colors.onSurface
                            )
                            Text(
                                text = minimumSystemRequirements.storage,
                                style = MaterialTheme.typography.caption,
                                color = MaterialTheme.colors.onBackground
                            )
                            Spacer(modifier = Modifier.height(height = 15.dp))
                            Text(
                                text = stringResource(id = R.string.lbl_graphics),
                                style = MaterialTheme.typography.caption,
                                color = MaterialTheme.colors.onSurface
                            )
                            Text(
                                text = minimumSystemRequirements.graphics,
                                style = MaterialTheme.typography.caption,
                                color = MaterialTheme.colors.onBackground
                            )
                            Spacer(modifier = Modifier.height(height = 15.dp))
                            Text(
                                text = stringResource(
                                    id = R.string.lbl_developer_copyright,
                                    mealDetail.developer
                                ),
                                fontSize = 11.sp,
                                color = Grey20
                            )
                            Spacer(modifier = Modifier.height(height = 30.dp))
                        }
                        LeadingIconButton(
                            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                            iconResId = R.drawable.ic_circle_info_solid,
                            textButton = stringResource(id = R.string.lbl_play_the_game),
                            onClick = {
                                onStepByStepClicked(mealDetail.mealUrl)
                            }
                        )
                        Spacer(modifier = Modifier.height(height = 20.dp))
                    }
                }
            }
        }
    }
}