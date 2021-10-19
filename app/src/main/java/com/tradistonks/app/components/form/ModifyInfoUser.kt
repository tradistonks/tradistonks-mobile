package com.tradistonks.app.components.form

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable

import androidx.compose.material.*
import androidx.compose.material.Text
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tradistonks.app.R
import com.tradistonks.app.components.fields.Email
import com.tradistonks.app.components.fields.EmailField
import androidx.navigation.NavHostController
import com.tradistonks.app.components.fields.Field
import com.tradistonks.app.models.requests.UserUpdateRequest
import com.tradistonks.app.ui.theme.colorGreen
import com.tradistonks.app.ui.theme.textColor
import com.tradistonks.app.web.services.auth.AuthentificationController
import kotlinx.coroutines.launch


@Composable
fun modifyInfoUserForm(navController: NavHostController, authController: AuthentificationController) {
    val user = authController.user
    val coroutineScope = rememberCoroutineScope()
    Text(
        text = stringResource(id = R.string.my_information),
        style = MaterialTheme.typography.h1,
        color = textColor
    )
    Box(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp)
    ){
        Column(modifier = Modifier.fillMaxWidth()) {
            val passwordFocusRequest = remember { FocusRequester() }
            val confirmationPasswordFocusRequest = remember { FocusRequester() }
            val emailState = remember { EmailField() }
            val fieldState = remember { Field() }
            Field(fieldState, onImeAction = { passwordFocusRequest.requestFocus() }, text = user!!.username)
            Spacer(modifier = Modifier.height(16.dp))
            Email(emailState, onImeAction = { passwordFocusRequest.requestFocus() }, text = user.email)
            /*Spacer(modifier = Modifier.height(16.dp))
            val passwordState = remember { PasswordField() }
            Password(
                label = stringResource(id = R.string.password),
                passwordState = passwordState,
                imeAction = ImeAction.Next,
                onImeAction = { confirmationPasswordFocusRequest.requestFocus() },
                modifier = Modifier.focusRequester(passwordFocusRequest)
            )

            Spacer(modifier = Modifier.height(16.dp))
            val confirmPasswordState = remember { ConfirmPasswordState(passwordState = passwordState) }
            Password(
                label = stringResource(id = R.string.confirm_password),
                passwordState = confirmPasswordState,
                onImeAction = { /*onSignUpSubmitted(emailState.text, passwordState.text)*/ },
                modifier = Modifier.focusRequester(confirmationPasswordFocusRequest)
            )

            Spacer(modifier = Modifier.height(16.dp))
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = "",
                    style = MaterialTheme.typography.caption
                )
            }*/

            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    coroutineScope.launch {
                        authController.updateUser(user._id, UserUpdateRequest(fieldState.text,
                            emailState.text, user.roles), navController)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                /*enabled = emailState.isValid &&
                        passwordState.isValid && confirmPasswordState.isValid,*/
                colors = ButtonDefaults.buttonColors(backgroundColor = colorGreen)
            ) {
                Text(
                    text = stringResource(id = R.string.modify_account)
                )
            }
        }
    }

}
