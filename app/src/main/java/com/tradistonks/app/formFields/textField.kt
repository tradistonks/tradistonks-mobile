package com.tradistonks.app.formFields

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

open class TextField(
    private val validator: (String) -> Boolean = { true },
    private val errorFor: (String) -> String = { "" }
) {
    var text: String by mutableStateOf("")
    var isFocusedDirty: Boolean by mutableStateOf(false)
    var isFocused: Boolean by mutableStateOf(false)
    private var displayErrors: Boolean by mutableStateOf(false)

    open val isValid: Boolean
        get() = validator(text)

    fun onFocusChange(focused: Boolean) {
        isFocused = focused
        if (focused) isFocusedDirty = true
    }

    fun enableShowErrors() {
        //l'erreur est affichée seulement si aumoin une fois le text a était en focus
        if (isFocusedDirty) {
            displayErrors = true
        }
    }

    fun showErrors() = !isValid && displayErrors

    open fun getError(): String? {
        return if (showErrors()) {
            errorFor(text)
        } else {
            null
        }
    }
}