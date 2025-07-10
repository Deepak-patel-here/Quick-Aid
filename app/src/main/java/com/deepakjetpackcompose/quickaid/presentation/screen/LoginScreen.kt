package com.deepakjetpackcompose.quickaid.presentation.screen

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavController
import com.deepakjetpackcompose.quickaid.R
import com.deepakjetpackcompose.quickaid.domain.navigation.NavigationHelper
import com.deepakjetpackcompose.quickaid.presentation.components.AuthText
import com.deepakjetpackcompose.quickaid.presentation.components.TextFieldAuth
import com.deepakjetpackcompose.quickaid.presentation.viewmodel.AuthState
import com.deepakjetpackcompose.quickaid.presentation.viewmodel.AuthViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException

@Composable
fun LoginScreen( navController: NavController, modifier: Modifier = Modifier, authViewModel: AuthViewModel= hiltViewModel()) {

    val input = remember { mutableStateOf("") }
    val passWord = remember { mutableStateOf("") }
    val passwordFocus = remember { FocusRequester() }
    val scrollState = rememberScrollState()
    val keyboardController = LocalSoftwareKeyboardController.current

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val authState = authViewModel.authState.collectAsState()

    LaunchedEffect(authState.value) {
        if(authState.value== AuthState.Loading){
            navController.navigate(NavigationHelper.AppScreen){
                popUpTo(NavigationHelper.LoginScreen){inclusive=true}
            }
        }
    }






    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(R.drawable.nurse),
            contentDescription = null,
            modifier = Modifier.size(150.dp)
        )
        Spacer(Modifier.height(50.dp))

        TextFieldAuth(
            input = input,
            leadingIcon = Icons.Default.Email,
            isTrailing = false,
            label = "Email",
            placeholder = "Enter email here",
            imeAction = ImeAction.Next,
            imeMethod = {
                passwordFocus.requestFocus()
            },
            trailingIcon1 = null,
            trailingIcon2 = null,
            isShow = true,

            )

        Spacer(Modifier.height(20.dp))


        TextFieldAuth(
            input = passWord,
            leadingIcon = Icons.Default.Lock,
            isTrailing = true,
            label = "Password",
            placeholder = "Enter Password here",
            imeAction = ImeAction.Done,
            imeMethod = {
                keyboardController?.hide()
            },
            trailingIcon1 = R.drawable.hide,
            trailingIcon2 = R.drawable.show,
            isShow = false,
            modifier = Modifier.focusRequester(passwordFocus)
        )
        Spacer(Modifier.height(50.dp))

        Button(
            onClick = {
                if(input.value.isNotEmpty() && passWord.value.isNotEmpty()){
                    authViewModel.loginUser (email = input.value, password = passWord.value){success,msg->
                        if(success){
                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                        }
                    }
                }else{
                    Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                }
             },
            modifier = Modifier
                .fillMaxWidth()
                .height(53.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Login",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
        Spacer(Modifier.height(20.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Divider(
                color = Color.Gray,
                modifier = Modifier
                    .weight(1f)
                    .height(1.dp)
            )

            Text(
                text = "  or Login with  ",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Divider(
                color = Color.Gray,
                modifier = Modifier
                    .weight(1f)
                    .height(1.dp)
            )
        }

        Spacer(Modifier.height(20.dp))


        Spacer(Modifier.height(20.dp))
        AuthText(text1 = "Don't have an account?", text2 = "Sign up", onClick = {
            navController.navigate(NavigationHelper.SignUpScreen) {
                popUpTo(NavigationHelper.LoginScreen) { inclusive = true }
            }
        }, modifier = Modifier.fillMaxWidth())


    }

}