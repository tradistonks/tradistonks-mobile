package com.tradistonks.app.formFields

class PasswordField :
    TextField(validator = ::isPasswordValid, errorFor = ::passwordValidationError)

class ConfirmPasswordField(private val passwordState: PasswordField) : TextField() {
    override val isValid
        get() = passwordAndConfirmationValid(passwordState.text, text)

    override fun getError(): String? {
        return if (showErrors()) {
            passwordConfirmationError(passwordState.text, text)
        } else {
            null
        }
    }
}

private fun passwordAndConfirmationValid(password: String, confirmedPassword: String): Boolean {
    return isPasswordValid(password) && password == confirmedPassword
}

private fun isPasswordValid(password: String): Boolean {
    return password.length > 3
}

private fun passwordValidationError(password: String): String {
    return "Incorrect Password"
}

private fun passwordConfirmationError(password: String, confirmedPassword: String): String {
    return "The Passwords don't match"
}