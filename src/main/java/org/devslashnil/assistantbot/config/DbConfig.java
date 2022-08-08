package org.devslashnil.assistantbot.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.inject.Qualifier;
import javax.sql.DataSource;

@Configuration
public class DbConfig {

    @Bean
    @Qualifier("bot-db")
    @ConfigurationProperties(prefix =  "app.db.bot-db")
    SpringDataJdbcProperties gitlabJdbcProperties() {
        return new SpringDataJdbcProperties();
    }

    @Bean
    @Qualifier("bot-db")
    public DataSource gitlabDataSource(@Qualifier("bot-db") SpringDataJdbcProperties properties) {
        return hikariDataSource("db", properties);
    }

    @Bean
    @Qualifier("bot-db")
    JdbcTemplate gitlabJdbcTemplate(@Qualifier("bot-db") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Data
    @NoArgsConstructor
    public static class SpringDataJdbcProperties {

        private static final String H2_DATABASE_DRIVER = "org.h2.Driver";

        /**
         * JDBC URL property
         */
        String url;

        /**
         * JDBC driver class name property
         */
        String driver;

        /**
         * JDBC username property
         */
        String username;

        /**
         * JDBC password property
         */
        String password;

        /**
         * Hikari / Verica maxPoolSize property
         */
        String poolSize;

        /**
         * Minimum pool size
         */
        int minPoolSize = 4;

        /**
         * Maximum pool size
         */
        int maxPoolSize = 10;

        /**
         * This property controls the maximum amount of time (in ms)
         * sit idle in the pool. A value of 0 means that idle connections are never removed from the pool.
         */
        long idleTimeout;

        /**
         * This property controls the maximum lifetime of a connection in the pool. When a connection
         * reaches timeout, even if recently used, it will be retired from the pool.
         * An in-use connection will never be retired, only when it is idle will be removed
         */
        long maxLifetime;

        /**
         * Bulk insert size
         */
        int bulkSize;

        /**
         * All-args constructor for {@link SpringDataJdbcProperies#toString()} (logging)
         *
         * @param url JDBC driver class name property
         * @param driver JDBC driver class name property
         * @param username JDBC username property
         * @param password JDBC password property
         * @param poolSize Hikari / Verica maxPoolSize property
         * @param bulkSize bulk insert size
         */
        public SpringDataJdbcProperies(
                String url, String driver,String username, String password, String poolSize, int bulkSize) {
            this.url = url;
            this.driver = driver;
            this.username = username;
            this.password = password;
            this.poolSize = poolSize;
            this.bulkSize = bulkSize;
        }

        /**
         * Return true, if instance describe in-memory H2 database
         *
         * @return true, if instance describe in-memory H2 database
         */
        public boolean isH2Databse() {
            return driver.equals(H2_DATABASE_DRIVER);
        }

        /**
         * Return string representation of instance converted to JSON
         *
         * @return string representation of instance converted to JSON
         */
    }

}
