package com.tradistonks.app.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tradistonks.app.R
import com.tradistonks.app.components.fields.Email
import com.tradistonks.app.components.fields.EmailField
import com.tradistonks.app.services.auth.AuthentificationRepository.login
import com.tradistonks.app.ui.theme.colorPink
import com.tradistonks.app.ui.theme.textColor


@Composable
fun SignInContent() {
    Text(
        text = stringResource(id = R.string.sign_in),
        style = MaterialTheme.typography.h1,
        color = textColor
    )
    Column(modifier = Modifier.fillMaxWidth()) {
        val focusRequester = remember { FocusRequester() }
        val emailState = remember { EmailField() }
        Email(emailState, onImeAction = { focusRequester.requestFocus() })

        Spacer(modifier = Modifier.height(16.dp))

        val passwordState = remember { PasswordField() }
        Password(
            label = stringResource(id = R.string.password),
            passwordState = passwordState,
            modifier = Modifier.focusRequester(focusRequester),
            /*onImeAction = { onSignInSubmitted(emailState.text, passwordState.text) }*/
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { /*login(emailState.text, passwordState.text)*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            enabled = emailState.isValid && passwordState.isValid,
            colors = ButtonDefaults.buttonColors(backgroundColor = colorPink)
        ) {
            Text(
                text = stringResource(id = R.string.sign_in)
            )
        }
    }
}
