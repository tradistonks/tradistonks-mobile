package com.tradistonks.app.pages

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tradistonks.app.GLOBAL_USER
import com.tradistonks.app.R
import com.tradistonks.app.components.Page
import com.tradistonks.app.components.form.modifyInfoUserForm
import com.tradistonks.app.ui.theme.colorPink
import com.tradistonks.app.ui.theme.colorYellow
import com.tradistonks.app.ui.theme.textColor

@Composable
fun Account(openDrawer: () -> Unit) {
    if(GLOBAL_USER!=null){
        Page(openDrawer, stringResource(R.string.title_page_account), { pageAccount()})
    }
    else{
        Page(openDrawer, stringResource(R.string.title_page_account), { pageConnexion() })
    }

}

@Composable
fun pageAccount(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        displayAccountInfo()
        //modifyInfoUserForm()
    }
}

@Composable
fun displayAccountInfo(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = stringResource(id = R.string.my_profil),
            style = MaterialTheme.typography.h1,
            color = textColor
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier) {
            Icon(
                Icons.Outlined.AccountCircle,
                contentDescription = stringResource(id = R.string.my_profil),
                Modifier.size(50.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Box(){
                Column() {
                    Text(
                        text = "Username :" + GLOBAL_USER!!.username,
                        style = MaterialTheme.typography.h2,
                        color = textColor
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Email :" + GLOBAL_USER!!.email,
                        style = MaterialTheme.typography.h2,
                        color = textColor
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { /*onSignUpSubmitted(emailState.text, passwordState.text)*/ },
            modifier = Modifier.width(300.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = colorPink)
        ) {
            Text(text = stringResource(id = R.string.modify_account))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { /*onSignUpSubmitted(emailState.text, passwordState.text)*/ },
            modifier = Modifier.width(300.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = colorYellow)
        ) {
            Text(text = stringResource(id = R.string.logout))
        }
    }
}