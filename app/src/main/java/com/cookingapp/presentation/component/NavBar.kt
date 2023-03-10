package com.cookingapp.presentation.component

import android.widget.Space
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cookingapp.R
/*
@Preview
@Composable
fun NavBarPre() {
    NavBar("title") {

    }
}*/
@Composable
fun NavBar(
    title: String,
    onBackPress: () -> Unit,
    onStepByStep: () -> Unit
){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { onBackPress() }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_circle_info_solid),
                contentDescription = "",
                tint = MaterialTheme.colors.onBackground
            )
        }
        Text(text = title)
        IconButton(onClick = { onStepByStep() }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_circle_info_solid),
                contentDescription = "",
                tint = MaterialTheme.colors.onBackground
            )
        }
        Spacer(modifier = Modifier.requiredWidth(26.dp))
    }

}