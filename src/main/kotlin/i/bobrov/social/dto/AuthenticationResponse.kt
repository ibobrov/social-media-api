package i.bobrov.social.dto

data class AuthenticationResponse(
    val accessToken: String,
    val refreshToken: String,
)
