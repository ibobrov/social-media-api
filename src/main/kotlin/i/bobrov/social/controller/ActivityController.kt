package i.bobrov.social.controller

import i.bobrov.social.dto.ActivityResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/activity")
@Tag(
    name = "Лента активностей",
    description = "Методы для работы с активностями",
)
class ActivityController {
    @PostMapping("/subscribe/{userName}")
    @Operation(summary = "Подписаться на пользователя")
    fun subscribe(
        @PathVariable userName: String,
    ) {
    }

    @PostMapping("/unsubscribe/{userName}")
    @Operation(summary = "Отписаться от пользователя")
    fun unsubscribe(
        @PathVariable userName: String,
    ) {
    }

    @GetMapping("/{limit}/{page}")
    @Operation(summary = "Получить список последних активностей")
    fun findLater(
        @PathVariable limit: Int,
        @PathVariable page: Int,
    ): List<ActivityResponse> =
        ArrayList()
}
