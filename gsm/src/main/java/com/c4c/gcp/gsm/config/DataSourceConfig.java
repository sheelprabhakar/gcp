package com.c4c.gcp.gsm.config;

import com.c4c.gcp.gsm.core.service.api.SecretReaderService;
import com.c4c.gcp.gsm.core.dto.DbCred;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;

@RefreshScope
@Configuration
public class DataSourceConfig {

    private final static Logger LOGGER = LoggerFactory.getLogger(DataSourceConfig.class);

    @Autowired
    private Environment env;
    @Autowired
    private SecretReaderService secretReaderService;
    @Bean
    @RefreshScope
    public DataSource dataSource(  ) throws IOException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(
                "jdbc:postgresql://localhost:5432/sample_db?currentSchema=test_schema");
        DbCred cred = this.secretReaderService.getActiveDbCred();
        if(cred == null){
            LOGGER.error("DB credential not found");
            throw new RuntimeException("DB credential not found");

        }
        System.out.println("DS initialize");
        dataSource.setUsername(cred.getUserName());
        dataSource.setPassword(cred.getPassword());
        return dataSource;

    }

}

