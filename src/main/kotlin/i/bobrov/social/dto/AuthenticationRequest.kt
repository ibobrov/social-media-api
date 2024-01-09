package i.bobrov.social.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Данные для аунтфикации")
data class AuthenticationRequest(
    @Schema(description = "Email пользователя")
    val email: String,
    @Schema(description = "Пароль пользователя")
    val password: String,
)
