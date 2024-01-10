package i.bobrov.social.controller

import i.bobrov.social.dto.UserRequest
import i.bobrov.social.dto.UserResponse
import i.bobrov.social.model.Role
import i.bobrov.social.model.User
import i.bobrov.social.service.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import java.util.UUID

@RestController
@RequestMapping("/api/user")
@Tag(
    name = "Пользователи",
    description = "Методы для работы с пользователями системы",
)
class UserController(
    private val userService: UserService,
) {
    @PostMapping
    @Operation(summary = "Создание пользователя")
    fun create(
        @RequestBody userRequest: UserRequest,
    ): UserResponse =
        userService
            .createUser(
                user = userRequest.toModel(),
            )?.toResponse()
            ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Пользователь не создан")

    @GetMapping
    @Operation(summary = "Получение всех пользователей")
    fun findAll(): List<UserResponse> =
        userService
            .findAll()
            .map { it.toResponse() }

    @GetMapping("/{uuid}")
    @Operation(summary = "Получение пользователя по UUID")
    fun findByUUID(
        @Parameter(description = "UUID пользователя")
        @PathVariable uuid: UUID,
    ): UserResponse =
        userService
            .findById(uuid)
            ?.toResponse()
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь не найден")

    @DeleteMapping("/{uuid}")
    @Operation(summary = "Удаление пользователя по UUID")
    fun deleteByUUID(
        @Parameter(description = "UUID пользователя")
        @PathVariable uuid: UUID,
    ): ResponseEntity<Boolean> {
        val success = userService.deleteByUUID(uuid)

        return if (success) {
            ResponseEntity
                .noContent()
                .build()
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь не найден")
        }
    }

    private fun UserRequest.toModel(): User =
        User(
            id = UUID.randomUUID(),
            email = this.email,
            name = "",
            password = this.password,
            role = Role.USER,
        )

    private fun User.toResponse(): UserResponse =
        UserResponse(
            uuid = this.id,
            email = this.email,
        )
}
