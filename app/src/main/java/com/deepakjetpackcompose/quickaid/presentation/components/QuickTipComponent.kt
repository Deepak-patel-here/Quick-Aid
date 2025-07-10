package com.deepakjetpackcompose.quickaid.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.deepakjetpackcompose.quickaid.domain.model.QuickTip

@Preview(showBackground = true)
@Composable
fun QuickTipComponent(modifier: Modifier = Modifier) {
    val quickTipsList = listOf(
        QuickTip(
            injury = "Minor Fracture",
            cure = "Immobilize the area, apply ice, and avoid movement. Control bleeding using a sterile cloth without touching protruding bones."
        ),
        QuickTip(
            injury = "Minor Burn",
            cure = "Cool the area under running water for 10 minutes. Avoid ice or ointments. Cover with a clean, non-stick bandage."
        ),
        QuickTip(
            injury = "Nosebleed",
            cure = "Sit upright and lean forward. Pinch the nose just above nostrils for 10 minutes. Avoid tilting head back."
        ),
        QuickTip(
            injury = "Sprain",
            cure = "Rest the limb, apply ice for 20 minutes, compress using a bandage, and elevate the area above heart level."
        ),
        QuickTip(
            injury = "Cut/Wound",
            cure = "Clean with water, apply antiseptic, and cover with a sterile bandage. Seek medical help if deep or bleeding heavily."
        ),
        QuickTip(
            injury = "Heat Stroke",
            cure = "Move to a cooler place, remove excess clothing, apply cool wet cloths, and drink water slowly. Seek medical help."
        )
    )

    Column(modifier = modifier
        .fillMaxWidth()
        .height(350.dp)
        .padding(20.dp)) {
        Text("Quick Tips", fontSize = 22.sp, fontWeight = FontWeight.SemiBold)

        Spacer(Modifier.height(10.dp))
        LazyColumn {
            items(quickTipsList) { value ->
                CardComponentForQuickTip(
                    quickTip = value,
                    modifier = Modifier.padding( vertical = 10.dp)
                )
            }
        }


    }
}