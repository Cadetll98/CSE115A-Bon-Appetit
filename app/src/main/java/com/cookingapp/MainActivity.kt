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

package com.cookingapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cookingapp.ui.theme.CookingAppTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.sql.Types.NULL

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // check for login info
        val intent = Intent(this, LoginUI::class.java)
        startActivity(intent)

        setContent {
            CookingAppTheme{    // Main theme for cooking app
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background    // set theme
                ) {
                    // Scaffold to ensure correct layering of UI. Top bar and action button
                    // on uppermost layer of UI
                    Scaffold (
                        floatingActionButton = {    // Creates the "+" floating button at bottom right
                            FloatingActionButton(onClick = {}) {    // action to perform after click
                                Icon(   // values for floating button
                                    imageVector = Icons.Default.Add,    // set image for button
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onPrimaryContainer     // set colors based on theme
                                )
                            }
                        },

                        // Top bar for home page
                        topBar = {
                            MediumTopAppBar( // Medium size top bar
                                title = {
                                    Text(text = "Cooking App")      // text on top bar
                                },
                                colors = TopAppBarDefaults.mediumTopAppBarColors(   // set colors for top bar
                                    containerColor = MaterialTheme.colorScheme.surfaceVariant,  // set colors for container
                                    titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant  // set color for text based on theme
                                )



                            )

                        },
                        bottomBar = {
                            BottomAppBar () {
                                Button(
                                    onClick = {
                                        Toast.makeText(baseContext,"You are logout",Toast.LENGTH_SHORT).show()
                                        val intent = Intent(baseContext, LoginUI::class.java)
                                        startActivity(intent)

                                    },
                                    contentPadding = PaddingValues(
                                        start = 20.dp,
                                        top = 12.dp,
                                        end = 20.dp,
                                        bottom = 12.dp
                                    )
                                )
                                {    // gives a logout button
                                    Icon(
                                        Icons.Filled.Close,
                                        contentDescription = "Favorite",
                                        modifier = Modifier.size(ButtonDefaults.IconSize),

                                    )
                                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                                    Text("Logout")
                                }
                            }
                        }




                    ) { values ->   // set values for scroller
                        LazyColumn (contentPadding = values) {  // lazy column to create scroller down
                            items(10) { // set amount of items in scroller
                                ImageCard(
                                    title = "FOOD NAME HERE", // text for card
                                    description = "DESCRIPTION OF THE FOOD HERE", // subtext for card
                                    modifier= Modifier.padding(8.dp)   // padding between cards
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
