package i.bobrov.social.controller

import i.bobrov.social.RunWithTestContext
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc

@RunWithTestContext
@AutoConfigureMockMvc
class FriendshipControllerTest {
    @Test
    fun test() {
        Assertions.assertThat("test").isEqualTo("test")
    }
}
