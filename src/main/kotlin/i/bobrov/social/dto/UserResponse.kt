package i.bobrov.social.dto

import io.swagger.v3.oas.annotations.media.Schema
import java.util.UUID

@Schema(description = "Информация о пользователе со строконы сервера")
data class UserResponse(
    @Schema(description = "Идентификатор пользователя")
    val uuid: UUID,
    @Schema(description = "Email пользователя")
    val email: String,
)
