package i.bobrov.social.controller

import i.bobrov.social.dto.AuthenticationRequest
import i.bobrov.social.dto.AuthenticationResponse
import i.bobrov.social.dto.RefreshTokenRequest
import i.bobrov.social.dto.TokenResponse
import i.bobrov.social.service.AuthenticationService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/auth")
@Tag(
    name = "Аутентификация",
    description = "Методы для получения/обновления токенов",
)
class AuthController(
    private val authenticationService: AuthenticationService,
) {
    @PostMapping
    @Operation(summary = "Получить токены")
    fun authenticate(
        @RequestBody authRequest: AuthenticationRequest,
    ): AuthenticationResponse =
        authenticationService.authentication(authRequest)

    @PostMapping("/refresh")
    @Operation(summary = "Обновить токены")
    fun refreshAccessToken(
        @RequestBody request: RefreshTokenRequest,
    ): TokenResponse =
        authenticationService
            .refreshAccessToken(request.token)
            ?.mapToTokenResponse()
            ?: throw ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid refresh token!")

    private fun String.mapToTokenResponse(): TokenResponse =
        TokenResponse(
            token = this,
        )
}
