package i.bobrov.social.controller

import i.bobrov.social.dto.PostRequest
import i.bobrov.social.dto.PostResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/post")
@Tag(
    name = "Посты",
    description = "Методы для работы с пользователями системы",
)
class PostController {
    @GetMapping("/{userName}/{page}")
    @Operation(summary = "Получить посты пользователя")
    fun findByUserName(
        @PathVariable userName: String,
        @PathVariable page: Int,
    ): List<PostResponse> =
        ArrayList()

    @PostMapping
    @Operation(summary = "Создание поста")
    fun create(
        @RequestBody postRequest: PostRequest,
    ) {
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновление поста")
    fun update(
        @PathVariable id: Int,
        @RequestBody postRequest: PostRequest,
    ) {
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление поста")
    fun delete(
        @PathVariable id: Int,
        @RequestBody postRequest: PostRequest,
    ) {
    }
}
