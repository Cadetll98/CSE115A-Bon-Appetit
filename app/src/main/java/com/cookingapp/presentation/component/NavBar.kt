package com.cookingapp.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cookingapp.R

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.sp

@Composable
fun NavBar(
    title: String,
    onBackPress: () -> Unit,
    onStepByStep: () -> Unit
){
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(48.dp)
        .padding(top = 8.dp)
        .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { onBackPress() }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_home),
                contentDescription = "Icon",
                modifier = Modifier.size(36.dp)
            )
        }
        Spacer(modifier = Modifier.fillMaxWidth().weight(1f))
        Text(text = title, fontSize = 18.sp)
        Spacer(modifier = Modifier.fillMaxWidth().weight(1f))
        IconButton(onClick = { onStepByStep() }) {
            Image(
                painter = painterResource(R.drawable.ic_step_by_step),
                contentDescription = "Icon",
                modifier = Modifier.size(36.dp)
            )
        }
    }

}