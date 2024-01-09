package i.bobrov.social.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Сгеннерированная пара токенов")
data class AuthenticationResponse(
    @Schema(description = "Access Token")
    val accessToken: String,
    @Schema(description = "Refresh Token")
    val refreshToken: String,
)
