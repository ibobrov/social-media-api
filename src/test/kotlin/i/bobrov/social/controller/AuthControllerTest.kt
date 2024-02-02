package i.bobrov.social.controller

import i.bobrov.social.RunWithTestContext
import i.bobrov.social.dto.AuthenticationRequest
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@RunWithTestContext
@AutoConfigureMockMvc
class AuthControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var authController: AuthController

    @Test
    fun whenAuthenticateUserThenResponse200() {
        mockMvc
            .perform(
                MockMvcRequestBuilders
                    .post("/api/auth")
                    .contentType("application/json")
                    .content("{\"email\":\"test@gmail.com\", \"password\":\"pass123\"}"),
            ).andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
    }

    // TODO
    @Test
    @Disabled
    fun whenRefreshAccessTokenThenResponse200() {
        val resp =
            authController.authenticate(
                AuthenticationRequest(
                    email = "test@gmail.com",
                    password = "pass123",
                ),
            )

        println(resp)

        mockMvc
            .perform(
                MockMvcRequestBuilders
                    .post("/api/auth/refresh")
                    .contentType("application/json")
                    .content("{\"token\":\"${resp.refreshToken}\"}"),
            ).andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
    }
}
