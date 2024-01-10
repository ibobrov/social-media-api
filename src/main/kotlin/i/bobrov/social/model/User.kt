package i.bobrov.social.model

import java.util.UUID

data class User(
    var id: UUID,
    val name: String,
    val email: String,
    val password: String,
    val role: Role,
)

enum class Role {
    USER,
    ADMIN,
}
