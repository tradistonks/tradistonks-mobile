package com.tradistonks.app.formFields
import java.util.regex.Pattern
// Considere un email valide s'il y a quelque text avant et après à "@"
private const val EMAIL_VALIDATION_REGEX = "^(.+)@(.+)\$"

class EmailField :
    TextField(validator = ::isEmailValid, errorFor = ::emailValidationError)

/**
 * Retourne une erreur à etre affichée ou un null s'il y a pas une erreur trouvée
 */
private fun emailValidationError(email: String): String {
    return "Invalid Email"
}

private fun isEmailValid(email: String): Boolean {
    return Pattern.matches(EMAIL_VALIDATION_REGEX, email)
}