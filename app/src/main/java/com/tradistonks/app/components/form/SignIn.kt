package com.tradistonks.app.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.material.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.material.CheckboxDefaults
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.tradistonks.app.R
import com.tradistonks.app.components.fields.Email
import com.tradistonks.app.components.fields.EmailField
import com.tradistonks.app.web.services.auth.AuthentificationController
import com.tradistonks.app.ui.theme.colorPink
import com.tradistonks.app.ui.theme.colorYellow
import com.tradistonks.app.ui.theme.colors
import com.tradistonks.app.ui.theme.textColor
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SignInContent(navController: NavHostController, authController: AuthentificationController) {
    val coroutineScope = rememberCoroutineScope()
    coroutineScope.launch {
        authController.retrieveLocalUser(navController)
    }

    Text(
        text = stringResource(id = R.string.sign_in),
        style = MaterialTheme.typography.h1,
        color = textColor
    )
    Column(modifier = Modifier.fillMaxWidth()) {
        val focusRequester = remember { FocusRequester() }
        val emailState = remember { EmailField() }
        Email(emailState, onImeAction = { focusRequester.requestFocus() })
        val checkedState = remember {
            androidx.compose.runtime.mutableStateOf(true)
        }

        Spacer(modifier = Modifier.height(16.dp))

        val passwordState = remember { PasswordField() }
        Password(
            label = stringResource(id = R.string.password),
            passwordState = passwordState,
            modifier = Modifier.focusRequester(focusRequester),
            /*onImeAction = { onSignInSubmitted(emailState.text, passwordState.text) }*/
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = checkedState.value,
                onCheckedChange = { checkedState.value = it },
                colors = CheckboxDefaults.colors(colorYellow)
            )

            Text(
                text = "Stay connected?",
                modifier = Modifier
                    .padding(start = 10.dp),
            )
        }

        Button(
            onClick = {
                coroutineScope.launch {
                    authController.login(emailState.text,
                        passwordState.text, checkedState.value,
                        navController)
                    }
                },
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
