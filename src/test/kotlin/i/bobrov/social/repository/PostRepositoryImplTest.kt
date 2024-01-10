package i.bobrov.social.repository

import i.bobrov.social.RunWithTestContext
import i.bobrov.social.model.Post
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import java.util.Calendar
import java.util.UUID

@RunWithTestContext
class PostRepositoryImplTest {
    @Autowired
    private lateinit var postRepo: PostRepositoryImpl

    @Autowired
    private lateinit var jdbcTemplate: NamedParameterJdbcTemplate

    private val userUuid = UUID.fromString("67e02c64-660a-4dd8-b056-b19a946cc535")

    private val instCalendar = Calendar.getInstance()

    @AfterEach
    fun reset() {
        jdbcTemplate.update(
            "delete from posts",
            mapOf<String, String>(),
        )
    }

    @Test
    fun whenTestFindById() {
        val post = Post(0, "Bug1", "text", "url", instCalendar, userUuid)
        postRepo.add(post)
        assertThat(postRepo.findById(post.id)).isEqualTo(post)
    }

    @Test
    fun whenTestFindAll() {
        val post1 = Post(0, "Bug1", "text", "url", instCalendar, userUuid)
        val post2 = Post(0, "Bug2", "text", "url", instCalendar, userUuid)
        postRepo.add(post1)
        postRepo.add(post2)
        assertThat(postRepo.findAll(0)).isEqualTo(listOf(post1, post2))
    }

    @Test
    fun whenReplaceItemIsSuccessful() {
        val post = Post(0, "Bug1", "text", "url", instCalendar, userUuid)
        postRepo.add(post)

        val updated = Post(0, "new post", "text", "url", instCalendar, userUuid)
        assertThat(postRepo.update(post.id, updated)).isTrue()

        val actual = postRepo.findById(post.id)!!
        assertThat(actual)
            .usingRecursiveComparison()
            .ignoringFields("title")
            .isEqualTo(post)
        assertThat(actual.title).isEqualTo("new post")
    }

    @Test
    fun whenReplaceItemIsNotSuccessful() {
        val post = Post(0, "Bug1", "text", "url", instCalendar, userUuid)
        postRepo.add(post)
        val updated = Post(0, "Bug2", "text2", "url2", instCalendar, userUuid)
        assertThat(postRepo.update(-1, updated)).isFalse()
        assertThat(postRepo.findById(post.id)).isEqualTo(post)
    }

    @Test
    fun whenDeleteItemIsSuccessful() {
        val post = Post(0, "Bug1", "text", "url", instCalendar, userUuid)
        postRepo.add(post)
        postRepo.delete(post.id)
        assertThat(postRepo.findById(post.id)).isNull()
    }

    @Test
    fun whenDeleteItemIsNotSuccessful() {
        val post = Post(0, "Bug1", "text", "url", instCalendar, userUuid)
        postRepo.add(post)
        assertThat(postRepo.delete(-1)).isFalse()
        assertThat(postRepo.findById(post.id)).isEqualTo(post)
    }
}
