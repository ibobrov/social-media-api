package i.bobrov.social.repository

import i.bobrov.social.model.User
import java.util.UUID

interface UserRepository {
    fun add(user: User): Boolean

    fun findByUUID(uuid: UUID): User?

    fun findByEmail(email: String): User?

    fun delete(uuid: UUID): Boolean

    fun findAll(): List<User>
}
