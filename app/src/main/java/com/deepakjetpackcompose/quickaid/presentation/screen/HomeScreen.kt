package com.deepakjetpackcompose.quickaid.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.deepakjetpackcompose.quickaid.presentation.components.QuickTipComponent
import com.deepakjetpackcompose.quickaid.presentation.components.UploadComponent

@Preview(showBackground = true)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (card, uploadBtn,quickTip) = createRefs()

        UploadComponent(modifier = Modifier.constrainAs(card) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })

        Button(
            onClick = {},
            modifier = Modifier
                .width(200.dp)
                .height(56.dp)
                .constrainAs(ref = uploadBtn) {
                    top.linkTo(card.bottom, margin = -30.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)

                },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Green)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxSize()
            ) {
                Icon(
                    imageVector = Icons.Default.CameraAlt,
                    contentDescription = null,
                )
                Spacer(Modifier.width(3.dp))
                Text("Use Camera")
            }
        }

        QuickTipComponent(modifier = Modifier.constrainAs (quickTip){

            top.linkTo(card.bottom, margin = 50.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)


        })

    }

}