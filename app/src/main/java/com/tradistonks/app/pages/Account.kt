package com.tradistonks.app.pages

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tradistonks.app.GLOBAL_USER
import com.tradistonks.app.MainActivity
import com.tradistonks.app.R
import com.tradistonks.app.components.Page
import com.tradistonks.app.ui.theme.textColor

@Composable
fun Account(openDrawer: () -> Unit) {
    Page(openDrawer, stringResource(R.string.title_page_account), { pageAccount() })
}

@Composable
fun pageAccount(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "My Profile",
            style = MaterialTheme.typography.h1,
            color = textColor
        )
        Icon(
            Icons.Outlined.AccountCircle,
            contentDescription = "Profile"
        )
        Text(
            text = "Username :" + GLOBAL_USER.username,
            style = MaterialTheme.typography.h2,
            color = textColor
        )
        Text(
            text = "Email :" + GLOBAL_USER.email,
            style = MaterialTheme.typography.h2,
            color = textColor
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { /*onSignUpSubmitted(emailState.text, passwordState.text)*/ },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = stringResource(id = R.string.modify_account))
        }
    }
}