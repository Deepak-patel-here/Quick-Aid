package com.deepakjetpackcompose.quickaid.presentation.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.deepakjetpackcompose.quickaid.R
import com.deepakjetpackcompose.quickaid.domain.navigation.NavigationHelper
import com.deepakjetpackcompose.quickaid.presentation.components.AuthText
import com.deepakjetpackcompose.quickaid.presentation.components.LoaderAnimationComponent
import com.deepakjetpackcompose.quickaid.presentation.components.TextFieldAuth
import com.deepakjetpackcompose.quickaid.presentation.viewmodel.AuthState
import com.deepakjetpackcompose.quickaid.presentation.viewmodel.AuthViewModel

@Composable
fun SignUpScreen(modifier: Modifier = Modifier, navController: NavController,authViewModel: AuthViewModel= hiltViewModel()) {
    val input = remember { mutableStateOf("") }
    val passWord = remember { mutableStateOf("") }
    val name=remember { mutableStateOf("") }
    val confirmPassword=remember { mutableStateOf("") }
    val passwordFocus = remember { FocusRequester() }
    val emailFocus = remember { FocusRequester() }
    val confirmPasswordFocus = remember { FocusRequester() }
    val scrollState = rememberScrollState()
    val keyboardController = LocalSoftwareKeyboardController.current
    val authState=authViewModel.authState.collectAsState()
    val context= LocalContext.current

    LaunchedEffect(authState.value) {
        if(authState.value== AuthState.Loading){
            navController.navigate(NavigationHelper.AppScreen){
                popUpTo(NavigationHelper.LoginScreen){inclusive=true}
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {

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
                input = name,
                leadingIcon = Icons.Default.Person,
                isTrailing = false,
                label = "Name",
                placeholder = "Enter Name here",
                imeAction = ImeAction.Next,
                imeMethod = {
                    emailFocus.requestFocus()
                },
                trailingIcon1 = null,
                trailingIcon2 = null,
                isShow = true,

                )

            Spacer(Modifier.height(20.dp))

            TextFieldAuth(
                input = input,
                leadingIcon = Icons.Default.Email,
                isTrailing = false,
                label = "Email",
                placeholder = "Enter Email here",
                imeAction = ImeAction.Next,
                imeMethod = {
                    passwordFocus.requestFocus()
                },
                trailingIcon1 = null,
                trailingIcon2 = null,
                isShow = true,
                modifier = Modifier.focusRequester(emailFocus)
            )

            Spacer(Modifier.height(20.dp))


            TextFieldAuth(
                input = passWord,
                leadingIcon = Icons.Default.Lock,
                isTrailing = true,
                label = "Password",
                placeholder = "Enter Password here",
                imeAction = ImeAction.Next,
                imeMethod = {
                    confirmPasswordFocus.requestFocus()
                },
                trailingIcon1 = R.drawable.hide,
                trailingIcon2 = R.drawable.show,
                isShow = false,
                modifier = Modifier.focusRequester(passwordFocus)
            )

            Spacer(Modifier.height(20.dp))


            TextFieldAuth(
                input = confirmPassword,
                leadingIcon = Icons.Default.Lock,
                isTrailing = true,
                label = "Confirm Password",
                placeholder = "Re-Enter Password here",
                imeAction = ImeAction.Done,
                imeMethod = {
                    keyboardController?.hide()
                },
                trailingIcon1 = R.drawable.hide,
                trailingIcon2 = R.drawable.show,
                isShow = false,
                modifier = Modifier.focusRequester(confirmPasswordFocus)
            )
            Spacer(Modifier.height(50.dp))

            Button(
                onClick = {
                    if (input.value.isNotEmpty() && passWord.value.isNotEmpty() && name.value.isNotEmpty() && confirmPassword.value.isNotEmpty()) {
                        if (passWord.value == confirmPassword.value) {
                            authViewModel.registerUser(
                                name = name.value,
                                email = input.value,
                                password = passWord.value
                            ) { success, msg ->
                                if (success) {
                                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                                } else {
                                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                                }
                            }
                        } else {
                            Toast.makeText(context, "Password does not match", Toast.LENGTH_SHORT)
                                .show()
                        }
                    } else {
                        Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.fillMaxWidth().height(53.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Sign Up",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(Modifier.height(20.dp))
            AuthText(text1 = "Already have an account?", text2 = "Login", onClick = {
                navController.navigate(NavigationHelper.LoginScreen) {
                    popUpTo(NavigationHelper.SignUpScreen) { inclusive = true }
                }
            }, modifier = Modifier.fillMaxWidth())


        }

        if(authState.value== AuthState.Loading){
            LoaderAnimationComponent()
        }
    }
}