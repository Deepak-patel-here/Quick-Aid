package com.deepakjetpackcompose.quickaid.presentation.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.font.FontWeight
import com.deepakjetpackcompose.quickaid.domain.model.QuickTip


@Composable
fun CardComponentForQuickTip(quickTip:QuickTip,modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (expanded) 1f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessVeryLow
        ),
        label = "BounceScale"
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .scale(scale)

            .animateContentSize( // Animates height based on content
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFEEEEEE))
                .padding(16.dp)
        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {


                Text(
                    text =quickTip.injury,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(Modifier.weight(1f))
                IconButton(onClick = {expanded = !expanded}) {
                    Icon(
                        imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))

            if (expanded) {
                Text(
                    text = quickTip.cure
                )
            }
        }
    }
}

