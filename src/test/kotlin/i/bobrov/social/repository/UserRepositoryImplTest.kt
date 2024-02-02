package i.bobrov.social.repository

import i.bobrov.social.RunWithTestContext
import i.bobrov.social.exception.ObjectNotCreatedException
import i.bobrov.social.model.Role
import i.bobrov.social.model.User
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import java.util.UUID

@RunWithTestContext
class UserRepositoryImplTest {
    @Autowired
    private lateinit var userRepo: UserRepositoryImpl

    @Autowired
    private lateinit var jdbcTemplate: NamedParameterJdbcTemplate

    private val emptyUuid = UUID.fromString("00000000-0000-0000-0000-000000000000")

    @BeforeEach
    fun cleanBefore() =
        clean()

    @AfterEach
    fun cleanAfter() =
        clean()

    private fun clean() {
        jdbcTemplate.update(
            "delete from users",
            mapOf<String, String>(),
        )
    }

    @Test
    fun whenAddAndFindByIdThenReturnUser() {
        val user = User(emptyUuid, "test", "test@email.test", "pass123", Role.USER)
        val addedUser = userRepo.add(user)
        val expected = userRepo.findByUUID(addedUser.id)
        assertThat(addedUser).isEqualTo(expected)
    }

    @Test
    fun whenAddAndCatchDuplicateKeyException() {
        val user = User(emptyUuid, "test", "test@email.test", "pass123", Role.USER)
        assertThrows<ObjectNotCreatedException> {
            userRepo.add(user)
            userRepo.add(user)
        }
    }

    @Test
    fun whenAddAndFindByEmailReturnUser() {
        val user = User(emptyUuid, "test", "test@email.test", "ignoring", Role.USER)
        userRepo.add(user)
        assertThat(userRepo.findByEmail(user.email)).isEqualTo(user)
        assertThat(userRepo.findByEmail("expect null")).isNull()
    }

    @Test
    fun whenFindAllThenReturnList() {
        val user = User(emptyUuid, "test", "test@email.test", "ignoring", Role.USER)
        val addedUser = userRepo.add(user)
        assertThat(userRepo.findAll()).isEqualTo(listOf(addedUser))
    }
}
