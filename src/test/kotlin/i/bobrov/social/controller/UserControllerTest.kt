package i.bobrov.social.controller

import i.bobrov.social.RunWithTestContext
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@RunWithTestContext
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    private val testUuid = "67e02c64-660a-4dd8-b056-b19a946cc535"

    @Test
    fun whenCreateUserThenResponse201() {
        mockMvc
            .perform(
                post("/api/user")
                    .contentType("application/json")
                    .content("{\"name\":\"bobrov\", \"email\":\"example@test.com\", \"password\":\"qwerty123\"}"),
            ).andDo(print())
            .andExpect(status().isCreated)
    }

    @Test
    fun whenTryCreateDuplicateThenResponse4xx() {
        mockMvc
            .perform(
                post("/api/user")
                    .contentType("application/json")
                    .content("{\"name\":\"bobrov\", \"email\":\"example@test.com\", \"password\":\"qwerty123\"}"),
            )
        mockMvc
            .perform(
                post("/api/user")
                    .contentType("application/json")
                    .content("{\"name\":\"bobrov\", \"email\":\"example@test.com\", \"password\":\"qwerty123\"}"),
            ).andDo(print())
            .andExpect(status().isBadRequest)
    }

    @Test
    @WithMockUser
    fun whenUserGetAllUserThenResponse403() {
        mockMvc
            .perform(get("/api/user"))
            .andDo(print())
            .andExpect(status().isForbidden)
    }

    @Test
    @WithMockUser(roles = ["ADMIN"])
    fun whenAdminGetAllUserThenResponse200() {
        mockMvc
            .perform(get("/api/user"))
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(
                content().json(
                    "[{\"uuid\":\"67e02c64-660a-4dd8-b056-b19a946cc535\",\"email\":\"test@gmail.com\"}]",
                ),
            )
    }

    @Test
    @WithMockUser
    fun whenUserGetUserByUuidThenResponse403() {
        mockMvc
            .perform(get("/api/user/$testUuid"))
            .andDo(print())
            .andExpect(status().isForbidden)
    }

    @Test
    @WithMockUser(roles = ["ADMIN"])
    fun whenAdminGetUserByUuidThenResponse200() {
        mockMvc
            .perform(get("/api/user/$testUuid"))
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(
                content().json(
                    "{\"uuid\":\"67e02c64-660a-4dd8-b056-b19a946cc535\",\"email\":\"test@gmail.com\"}",
                ),
            )
    }
}
