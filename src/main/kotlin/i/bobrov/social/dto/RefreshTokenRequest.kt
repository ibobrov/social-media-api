package i.bobrov.social.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Refresh Token для обновления")
data class RefreshTokenRequest(
    @Schema(description = "Токен")
    val token: String,
)
