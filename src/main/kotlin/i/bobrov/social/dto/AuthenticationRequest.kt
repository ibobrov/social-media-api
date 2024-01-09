package i.bobrov.social.dto

data class AuthenticationRequest(
    val email: String,
    val password: String,
)
