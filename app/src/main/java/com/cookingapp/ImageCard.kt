// Filename: ImageCard.kt
//
// This file contains the values for creating the ImageCard displayed on the homepage

package com.cookingapp
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode
import kotlin.random.Random

// OptIn to use experimental Material3Api
@OptIn(ExperimentalMaterial3Api::class)
@Composable
// Define function ImageCard
fun ImageCard(
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors( // set default colors
            containerColor = MaterialTheme.colorScheme.surfaceVariant,  // set color for container
        ),
        shape = MaterialTheme.shapes.large  // set shape for theme
    ) {
        Image(  // define image for card block on furthest back
            painter = rememberAsyncImagePainter(
                model = "https://picsum.photos/seed/${Random.nextInt()}/300/200"    // temporary randome image for cards
            ),
            contentDescription = null,  // Description for image content
            modifier = Modifier
                .clip(MaterialTheme.shapes.large)   // set shape box around image
                .fillMaxWidth() // fill all screen
                .aspectRatio(3f/2f)     // set dimensions  for card for
        )
        Column(
            modifier = Modifier.padding(16.dp)      // set padding around bolumn
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge     // set text type
            )
            Spacer(modifier = Modifier.height(8.dp))    // leave space between cards
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium     // make smaller text for subtext
            )
            Spacer(modifier = Modifier.height(8.dp)) // spacer between subtext and buttons
            FlowRow(
                modifier = Modifier.fillMaxWidth(),     // spacer between buttons
                mainAxisSpacing = 8.dp,
                mainAxisSize = SizeMode.Wrap
            ) {
                // AssistChip to create clickable buttons
                AssistChip(
                    onClick = { },  // action to perform after click
                    colors = AssistChipDefaults.assistChipColors(   // set colors or button
                        leadingIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant    // set colors based on theme
                    ),
                    leadingIcon = {
                        Icon(   // set value icon for button
                            imageVector = Icons.Outlined.FavoriteBorder,    // Sets the image on button
                            contentDescription = null
                        )
                    },
                    label = {
                        Text(text = "Mark as favorite")     // Text displayed on button
                    }
                )
                AssistChip(
                    onClick = { },      // action to perform after click
                    colors = AssistChipDefaults.assistChipColors(       // set colors or button
                        leadingIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant    // set colors based on theme
                    ),
                    leadingIcon = {
                        Icon(   // set values icon for button
                            imageVector = Icons.Outlined.FavoriteBorder,    // Sets image on button
                            contentDescription = null
                        )
                    },
                    label = {
                        Text(text = "Share with others")    // Text displayed on button
                    }
                )
            }
        }
    }
}