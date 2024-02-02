package i.bobrov.social.controller

import i.bobrov.social.RunWithTestContext
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.test.web.servlet.MockMvc

@RunWithTestContext
@AutoConfigureMockMvc
class PostControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun test() {
    }
}
