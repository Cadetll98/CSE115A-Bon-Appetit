// Filename: MainActivity.kt
//
// This file contains the main wrapper function for the CookingApp.
// This program will be a fully functioning Android Mobile application. This application
// uses Kotlin.
// This application will contain a variety of meals and provide step-by-step instructions
// on how to prepare them.
// Usage: This program will be fully functioning on most mobiles and can be ran on
// Android Studio.
//
// Fernando Ortiz

package com.cookingapp.presentation.activity


// this tells hilt that this component will receive dependencies from a parent class in our
// case it is the Application() class that we have created
// Under the hood hilt will generate an individual hilt component for each Android class in our
// project
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalUriHandler
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.cookingapp.LoginUI
import com.cookingapp.presentation.screen.base.Index
import com.cookingapp.presentation.screen.base.Screen
import com.cookingapp.presentation.theme.CookingAppTheme
import com.cookingapp.util.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                mainViewModel.splashScreenVisible.value
            }
        }
        setContent {
            val scaffoldState = rememberScaffoldState()
            val navController = rememberNavController()
            val availableMeals by mainViewModel.meals.collectAsState()
            val scope = rememberCoroutineScope()
            val uriHandler = LocalUriHandler.current

            CookingAppTheme(
                darkTheme = isSystemInDarkTheme()
            ) {
                Index(
                    scaffoldState = scaffoldState,
                    navController = navController,
                    availableMeals = availableMeals,
                    onOpenDrawer = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    },
                    onSearchButtonClick = {
                        scope.launch {
                            val path = "search?mode=$SEARCH_MODE_KEY"
                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                key = ALL_MEALS_KEY,
                                value = availableMeals.data?: emptyList()
                            )
                            navController.navigate(route = path)
                        }
                    },
                    onMealClick = { mealId ->
                        navController.navigate(route = "mealDetail/$mealId")
                    },
                    onPlayTheGameClicked = { mealUrl ->
                        uriHandler.openUri(uri = mealUrl)
                    },
                    onHomeMenuClick = {
                        scope.launch {
                            scaffoldState.drawerState.close()
                            navController.navigate(route = Screen.HomeScreen.route)
                        }
                    },
                    onPCGamesClick = {
                        scope.launch {
                            val path = "search?mode=$FILTER_MODE_KEY&filter=$PC_GAMES"
                            scaffoldState.drawerState.close()
                            navController.navigate(route = path)
                        }
                    },
                    onWebGamesClick = {
                        scope.launch {
                            val path = "search?mode=$FILTER_MODE_KEY&filter=$BROWSER_GAMES"
                            scaffoldState.drawerState.close()
                            navController.navigate(route = path)
                        }
                    },
                    onLatestMealsClick = {
                        scope.launch {
                            val path = "search?mode=$FILTER_MODE_KEY&filter=$LATEST_MEALS"
                            scaffoldState.drawerState.close()
                            navController.navigate(route = path)
                        }
                    },
                    onLogoutClick = {
                        Toast.makeText(baseContext,"You are logout", Toast.LENGTH_SHORT).show()
                        val intent = Intent(baseContext, LoginUI::class.java)
                        intent.putExtra("NeedLogout", true)
                        this.startActivity(intent)
                    },
                )
            }
        }
    }
}


