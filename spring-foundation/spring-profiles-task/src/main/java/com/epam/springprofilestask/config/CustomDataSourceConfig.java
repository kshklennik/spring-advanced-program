/*
 * (c) 2024 EPAM systems, Inc. All Rights Reserved. All information contained herein is, and remains the
 * property of EPAM Systems, Inc. and/or its suppliers and is protected by international intellectual
 * property law. Dissemination of this information or reproduction of this material is strictly forbidden,
 * unless prior written permission is obtained from EPAM Systems, Inc
 */

package com.epam.springprofilestask.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class CustomDataSourceConfig {

    @Bean
    @ConditionalOnProperty(name = "application.custom-datasource.enabled",
        havingValue = "false",
        matchIfMissing = true)
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConditionalOnProperty(name = "application.custom-datasource.enabled",
        havingValue = "true",
        matchIfMissing = false)
    @ConfigurationProperties(prefix = "application.custom-datasource.datasource")
    public DataSource dataSourceCustom() {
        return DataSourceBuilder.create().build();
    }
}