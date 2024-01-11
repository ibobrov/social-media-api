package i.bobrov.social.controller

import i.bobrov.social.dto.PostResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/friendship")
@Tag(
    name = "Заявки в друзья",
    description = "Методы для работы с заявками в друзья",
)
class FriendshipController {
    @GetMapping
    @Operation(summary = "Получить активные заяки")
    fun findActive(): List<PostResponse> =
        ArrayList()

    @PostMapping("/out/new/{userName}")
    @Operation(summary = "Отправить пользователю заяку в друзья")
    fun send(
        @PathVariable userName: String,
    ) {
    }

    @PostMapping("/out/recall/{userName}")
    @Operation(summary = "Отозвать заяку в друзья")
    fun recall(
        @PathVariable userName: String,
    ) {
    }

    @PostMapping("/in/accept/{userName}")
    @Operation(summary = "Принять заяку в друзья")
    fun accept(
        @PathVariable userName: String,
    ) {
    }

    @PostMapping("/in/close/{userName}")
    @Operation(summary = "Отклонить заяку в друзья")
    fun close(
        @PathVariable userName: String,
    ) {
    }
}
