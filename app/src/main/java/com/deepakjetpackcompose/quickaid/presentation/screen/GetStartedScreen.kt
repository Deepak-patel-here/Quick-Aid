package com.deepakjetpackcompose.quickaid.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.deepakjetpackcompose.quickaid.R
import com.deepakjetpackcompose.quickaid.domain.consatant.onBoardingSubTitle1
import com.deepakjetpackcompose.quickaid.domain.consatant.onBoardingSubTitle2
import com.deepakjetpackcompose.quickaid.domain.consatant.onBoardingTitle1
import com.deepakjetpackcompose.quickaid.domain.consatant.onBoardingTitle2
import com.deepakjetpackcompose.quickaid.domain.navigation.NavigationHelper
import com.deepakjetpackcompose.quickaid.presentation.components.OnBoardingComponent

@Composable
fun GetStartedScreen(navController: NavController, modifier: Modifier = Modifier) {

    var screen by  remember{ mutableIntStateOf(1) }
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(bottom = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if(screen==1) OnBoardingComponent(img = R.drawable.first_aid, title = onBoardingTitle1, subTitle = onBoardingSubTitle1)
            else OnBoardingComponent(img = R.drawable.map_navigation, title = onBoardingTitle2, subTitle = onBoardingSubTitle2)
            Spacer(Modifier.height(50.dp))
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomEnd)
                .padding(bottom = 30.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if(screen!=1) {
                Text(
                    "Back",
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp,
                    modifier = Modifier.padding(5.dp)
                        .clickable(onClick = {
                            screen=1
                        })
                )
            }
            Spacer(Modifier.weight(1f))
            Button(
                onClick = { if(screen==1)screen=2 else{
                    navController.navigate(NavigationHelper.LoginScreen){
                        popUpTo(NavigationHelper.OnBoardingScreen){inclusive=true}
                    }
                } },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.width(170.dp)
            ) {
                Text(
                    text=if(screen==1)"Next" else "Get Started",
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    letterSpacing = 1.sp,
                    modifier = Modifier.padding(5.dp)
                )
            }
        }
    }

}