package i.bobrov.social.controller

import i.bobrov.social.dto.FriendResponse
import i.bobrov.social.dto.MessageRequest
import i.bobrov.social.dto.MessageResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/friend")
@Tag(
    name = "Друзья",
    description = "Методы для взаимодействия со списком друзей",
)
class FriendController {
    @GetMapping
    @Operation(summary = "Получить список текущих друзей")
    fun findAll(): List<FriendResponse> =
        ArrayList()

    @DeleteMapping("/{userName}")
    @Operation(summary = "Удалить из друзей")
    fun delete(
        @PathVariable userName: String,
    ) {
    }

    @GetMapping("/msg/get/{userName}")
    @Operation(summary = "Получить сообщения друга")
    fun findMsg(
        @PathVariable userName: String,
    ): List<MessageResponse> =
        ArrayList()

    @PostMapping("/msg/send/{userName}")
    @Operation(summary = "Отправить сообщение другу")
    fun sendMsg(
        @PathVariable userName: String,
        @RequestBody msgRequest: MessageRequest,
    ) {
    }
}
