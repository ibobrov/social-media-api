package i.bobrov.social.repository

import i.bobrov.social.model.Post
import i.bobrov.social.util.getCalendar
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class PostRepositoryImpl(
    private val jdbcTemplate: NamedParameterJdbcTemplate,
) : PostRepository {
    override fun add(elm: Post): Int {
        val keyHolder = GeneratedKeyHolder()
        jdbcTemplate.update(
            "insert into posts (title, text, image_url, create_time, author_user_id)" +
                "values (:title, :text, :image_url, :create_time, :author_user_id)",
            MapSqlParameterSource(
                mapOf(
                    "title" to elm.title,
                    "text" to elm.text,
                    "image_url" to elm.imageUrl,
                    "create_time" to elm.createTime,
                    "author_user_id" to elm.userId,
                ),
            ),
            keyHolder,
            listOf("id").toTypedArray(),
        )
        val id = keyHolder.keys?.getValue("id") as Int
        elm.id = id
        return id
    }

    override fun update(id: Int, elm: Post): Boolean =
        jdbcTemplate.update(
            "update posts set title = :title, text = :text, image_url = :image_url where id = :id",
            mapOf(
                "id" to id,
                "text" to elm.text,
                "title" to elm.title,
                "image_url" to elm.imageUrl,
            ),
        ) > 0

    override fun delete(id: Int): Boolean =
        jdbcTemplate.update(
            "delete from posts where id = :id",
            mapOf(
                "id" to id,
            ),
        ) > 0

    override fun findById(id: Int): Post? =
        jdbcTemplate
            .query(
                "select * from posts where id = :id",
                mapOf(
                    "id" to id,
                ),
                rowMapper,
            ).firstOrNull()

    override fun findAll(pageIndex: Int): List<Post> =
        jdbcTemplate.query(
            "select * from posts order by id limit :limit offset :offset",
            mapOf(
                "limit" to PAGE_SIZE,
                "offset" to PAGE_SIZE * pageIndex,
            ),
            rowMapper,
        )

    private companion object {
        const val PAGE_SIZE = 10
        val rowMapper =
            RowMapper<Post> { rs, _ ->
                Post(
                    id = rs.getInt("id"),
                    title = rs.getString("title"),
                    text = rs.getString("text"),
                    imageUrl = rs.getString("image_url"),
                    createTime = rs.getCalendar("create_time"),
                    userId = UUID.fromString(rs.getString("author_user_id")),
                )
            }
    }
}
