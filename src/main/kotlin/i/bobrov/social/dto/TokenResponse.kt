package i.bobrov.social.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Token для авторизации")
data class TokenResponse(
    @Schema(description = "Token")
    val token: String,
)
