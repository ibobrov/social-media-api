package i.bobrov.social.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.jdbc.datasource.DriverManagerDataSource
import javax.sql.DataSource

@Configuration
class SpringJdbcConfig {
    @Value("\${spring.datasource.url}")
    private lateinit var url: String

    @Value("\${spring.datasource.username}")
    private lateinit var username: String

    @Value("\${spring.datasource.password}")
    private lateinit var password: String

    @Value("\${spring.datasource.driver-class-name}")
    private lateinit var driver: String

    @Bean
    @Profile("prod")
    fun dataSource() =
        DriverManagerDataSource().also {
            it.setDriverClassName(driver)
            it.url = url
            it.username = username
            it.password = password
        }

    @Bean
    fun namedParameterJdbcOperations(dataSource: DataSource) =
        NamedParameterJdbcTemplate(dataSource)

    @Bean
    fun transactionManager(dataSource: DataSource) =
        DataSourceTransactionManager(dataSource)
}
