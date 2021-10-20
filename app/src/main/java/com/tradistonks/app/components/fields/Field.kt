package com.tradistonks.app.components.fields

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import java.util.regex.Pattern
import com.tradistonks.app.R
// Considere un email valide s'il y a quelque text avant et après à "@"
private const val FIELD_VALIDATION_REGEX = "^(.+)\$"

class Field :
    TextField(validator = ::isFieldValid, errorFor = ::fieldValidationError)

/**
 * Retourne une erreur à etre affichée ou un null s'il y a pas une erreur trouvée
 */
private fun fieldValidationError(text: String): String {
    return "Invalid Field value"
}

private fun isFieldValid(text: String): Boolean {
    return Pattern.matches(FIELD_VALIDATION_REGEX, text)
}

@Composable
fun Field(
    fieldState: TextField = remember { Field() },
    imeAction: ImeAction = ImeAction.Next,
    onImeAction: () -> Unit = {},
    text : String = stringResource(id = R.string.email)
) {
    OutlinedTextField(
        value = fieldState.text,
        onValueChange = {
            fieldState.text = it
        },
        label = {
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = text,
                    style = MaterialTheme.typography.body2
                )
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged { focusState ->
                fieldState.onFocusChange(focusState.isFocused)
                if (!focusState.isFocused) {
                    fieldState.enableShowErrors()
                }
            },
        textStyle = MaterialTheme.typography.body2,
        isError = fieldState.showErrors(),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = imeAction),
        keyboardActions = KeyboardActions(
            onDone = {
                onImeAction()
            }
        )
    )
    fieldState.getError()?.let { error -> TextFieldError(textError = error) }
}
