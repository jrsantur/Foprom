package pe.juabsa.cipca.foprom.ui.login

import pe.juabsa.cipca.foprom.ui.login.LoggedInUserView

/**
 * Authentication result : success (user details) or error message.
 */
data class LoginResult(
        val success: LoggedInUserView? = null,
        val error: Int? = null
)
