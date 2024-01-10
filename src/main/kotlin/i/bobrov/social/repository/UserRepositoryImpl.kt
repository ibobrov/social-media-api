package i.bobrov.social.repository

import i.bobrov.social.model.Role
import i.bobrov.social.model.User
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class UserRepositoryImpl(
    private val jdbcTemplate: NamedParameterJdbcTemplate,
    private val encoder: PasswordEncoder,
) : UserRepository {
    override fun add(user: User): Boolean {
        val keyHolder = GeneratedKeyHolder()
        jdbcTemplate.update(
            "insert into users (id, name, email, password, role)" +
                "values (uuid_generate_v4(), :name, :email, :password, :role)",
            MapSqlParameterSource(
                mapOf(
                    "name" to user.name,
                    "email" to user.email,
                    "password" to encoder.encode(user.password),
                    "role" to user.role,
                ),
            ),
            keyHolder,
            listOf("id").toTypedArray(),
        )
        val id = keyHolder.keys?.getValue("id") as String
        user.id = UUID.fromString(id)
        return true
    }

    override fun findByUUID(uuid: UUID): User? =
        jdbcTemplate
            .query(
                "select * from users where id = :id",
                mapOf(
                    "id" to uuid,
                ),
                rowMapper,
            ).firstOrNull()

    override fun findByEmail(email: String): User? =
        jdbcTemplate
            .query(
                "select * from users where email = :email",
                mapOf(
                    "email" to email,
                ),
                rowMapper,
            ).firstOrNull()

    override fun delete(uuid: UUID): Boolean =
        jdbcTemplate.update(
            "delete from users where id = :id",
            mapOf(
                "id" to uuid,
            ),
        ) > 0

    override fun findAll(): List<User> =
        jdbcTemplate.query(
            "select * from users",
            rowMapper,
        )

    private companion object {
        val rowMapper =
            RowMapper<User> { rs, _ ->
                User(
                    id = UUID.fromString(rs.getString("id")),
                    name = rs.getString("name"),
                    email = rs.getString("email"),
                    password = rs.getString("password"),
                    role = Role.valueOf(rs.getString("role")),
                )
            }
    }
}
