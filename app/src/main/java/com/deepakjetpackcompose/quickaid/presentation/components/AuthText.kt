package com.deepakjetpackcompose.quickaid.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun AuthText(text1:String,text2:String,onClick:()->Unit,modifier: Modifier = Modifier) {

    Row (modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center){
        Text(text1, modifier = Modifier, fontSize = 14.sp,fontWeight = FontWeight.SemiBold)
        Text(text2,fontSize = 16.sp, fontWeight = FontWeight.Bold, modifier = Modifier.clickable(onClick = onClick), color = MaterialTheme.colorScheme.primary)
    }

}