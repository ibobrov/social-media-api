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
import org.springframework.security.access.prepost.PreAuthorize
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
    private val nullUUID = UUID.fromString("00000000-0000-0000-0000-000000000000")

    @PostMapping
    @Operation(summary = "Создание пользователя (не требуется аунтификация)")
    fun create(
        @RequestBody userRequest: UserRequest,
    ) = ResponseEntity<UserResponse>(
        userService
            .createUser(
                user = userRequest.toModel(),
            ).toResponse(),
        HttpStatus.CREATED,
    )

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Получение всех пользователей (администратор)")
    fun findAll(): List<UserResponse> =
        userService
            .findAll()
            .map { it.toResponse() }

    @GetMapping("/{uuid}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Получение пользователя по UUID (администратор)")
    fun findByUUID(
        @Parameter(description = "UUID пользователя")
        @PathVariable uuid: UUID,
    ): UserResponse =
        userService
            .findById(uuid)
            ?.toResponse()
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь не найден")

    @DeleteMapping("/{uuid}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Удаление пользователя по UUID (администратор)")
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
            id = nullUUID,
            email = this.email,
            name = this.name,
            password = this.password,
            role = Role.USER,
        )

    private fun User.toResponse(): UserResponse =
        UserResponse(
            uuid = this.id,
            email = this.email,
        )
}
