package i.bobrov.social.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Информация о пользователе со строконы клиента")
data class UserRequest(
    @Schema(description = "Email пользователя")
    val email: String,
    @Schema(description = "Пароль пользователя")
    val password: String,
)
