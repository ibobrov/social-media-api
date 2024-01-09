package i.bobrov.social.service

import i.bobrov.social.model.User
import i.bobrov.social.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserService(
    private val userRepo: UserRepository,
) {
    fun createUser(user: User): User? {
        val found = userRepo.findByEmail(user.email)

        return if (found == null) {
            userRepo.save(user)
            user
        } else {
            null
        }
    }

    fun findById(uuid: UUID): User? =
        userRepo.findByUUID(uuid)

    fun findAll() =
        userRepo.findAll()

    fun deleteByUUID(uuid: UUID): Boolean =
        userRepo.deleteByUUID(uuid)
}
