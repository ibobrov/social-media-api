package i.bobrov.social.controller

import i.bobrov.social.RunWithTestContext
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc

@RunWithTestContext
@AutoConfigureMockMvc
class ActivityControllerTest {
    @Test
    fun test() {
        assertThat("test").isEqualTo("test")
    }
}
