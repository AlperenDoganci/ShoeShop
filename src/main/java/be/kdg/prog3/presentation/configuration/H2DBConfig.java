package be.kdg.prog3.presentation.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("jdbc_template")
public class H2DBConfig {
    private final Logger log = LoggerFactory.getLogger(H2DBConfig.class);
    @Bean
    public DataSource dataSource() {
        log.info("Using H2db as data source...");
         return DataSourceBuilder
                .create()
                .driverClassName("org.h2.Driver")
                .url("jdbc:h2:mem:shoeshopdb")
                .username("sa")
                .password("")
                .build();
    }
}
