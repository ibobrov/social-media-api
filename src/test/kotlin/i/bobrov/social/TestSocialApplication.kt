package i.bobrov.social

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.fromApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.boot.with
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Profile
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.test.context.ActiveProfiles
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.utility.DockerImageName

@TestConfiguration(proxyBeanMethods = true)
class TestSocialApplication {
    @Value("\${spring.datasource.driver-class-name}")
    private lateinit var driver: String

    @Bean
    @ServiceConnection
    fun postgresContainer(): PostgreSQLContainer<*> =
        PostgreSQLContainer(DockerImageName.parse("postgres:latest"))

    @Bean
    @Profile("test")
    fun dataSource(postgres: PostgreSQLContainer<*>) =
        DriverManagerDataSource().also {
            it.setDriverClassName(driver)
            it.url = postgres.getJdbcUrl()
            it.username = postgres.username
            it.password = postgres.password
        }
}

@ActiveProfiles("test")
@SpringBootTest(classes = [TestSocialApplication::class])
annotation class RunWithTestContext

fun main(args: Array<String>) {
    fromApplication<SocialApplication>().with(TestSocialApplication::class).run(*args)
}
