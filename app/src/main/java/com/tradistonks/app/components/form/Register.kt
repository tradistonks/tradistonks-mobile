package com.tradistonks.app.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable

import androidx.compose.material.*
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tradistonks.app.R
import com.tradistonks.app.components.fields.Email
import com.tradistonks.app.components.fields.EmailField
import androidx.compose.ui.text.input.ImeAction
import androidx.navigation.NavHostController
import com.tradistonks.app.components.fields.Field
import com.tradistonks.app.web.services.auth.AuthentificationController
import com.tradistonks.app.models.Register
import com.tradistonks.app.ui.theme.colorPink
import com.tradistonks.app.ui.theme.textColor

@Composable
fun RegisterContent(navController: NavHostController, authController: AuthentificationController) {
    Text(
        text = stringResource(id = R.string.register),
        style = MaterialTheme.typography.h1,
        color = textColor
    )
    Column(modifier = Modifier.fillMaxWidth()) {
        val passwordFocusRequest = remember { FocusRequester() }
        val confirmationPasswordFocusRequest = remember { FocusRequester() }
        val emailState = remember { EmailField() }
        val fieldState = remember { Field() }
        Field(fieldState, onImeAction = { passwordFocusRequest.requestFocus() }, text = "Username")
        Spacer(modifier = Modifier.height(16.dp))
        Email(emailState, onImeAction = { passwordFocusRequest.requestFocus() })

        Spacer(modifier = Modifier.height(16.dp))
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
                text = stringResource(id = R.string.terms_and_conditions),
                style = MaterialTheme.typography.caption
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { /*onSignUpSubmitted(emailState.text, passwordState.text)*/
                authController.register(Register(emailState.text, fieldState.text, passwordState.text, confirmPasswordState.text))
                        navController.navigate("connexion")},
            modifier = Modifier.fillMaxWidth(),
            enabled = emailState.isValid &&
                    passwordState.isValid && confirmPasswordState.isValid,
            colors = ButtonDefaults.buttonColors(backgroundColor = colorPink)
        ) {
            Text(text = stringResource(id = R.string.create_account))
        }
    }
}
