package com.deepakjetpackcompose.quickaid.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun TextFieldAuth(
    input: MutableState<String>,
    leadingIcon: ImageVector,
    trailingIcon1: Int?,
    trailingIcon2: Int?,
    isTrailing: Boolean?,
    label: String,
    placeholder: String,
    imeAction: ImeAction,
    imeMethod: () -> Unit,
    isShow: Boolean,
    modifier: Modifier = Modifier
) {

    val isShow = remember { mutableStateOf(isShow) }
    OutlinedTextField(
        value = input.value,
        onValueChange = { input.value = it },
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        label = { Text(label) },
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null
            )
        },
        trailingIcon = {
            if (isTrailing == true) {
                IconButton(onClick = { isShow.value = !isShow.value}) {
                    Icon(
                        painter = if (isShow.value) painterResource(trailingIcon2!!) else painterResource(
                            trailingIcon1!!
                        ),
                        contentDescription = null,
                        modifier = Modifier
                            .size(25.dp)

                    )
                }
            }
        },
        placeholder = { Text(placeholder) },
        singleLine = true,
        maxLines = 1,
        minLines = 1,
        keyboardOptions = KeyboardOptions(imeAction = imeAction),
        keyboardActions = KeyboardActions(onNext = {
            imeMethod()
        }),
        visualTransformation = if (isShow.value) VisualTransformation.None else PasswordVisualTransformation()


    )

}