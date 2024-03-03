/*
 * Copyright © 2023 EPAM Systems, Inc. All Rights Reserved. All information contained herein is, and remains the
 * property of EPAM Systems, Inc. and/or its suppliers and is protected by international intellectual
 * property law. Dissemination of this information or reproduction of this material is strictly forbidden,
 * unless prior written permission is obtained from EPAM Systems, Inc
 */

package com.epam.cloudserviceimpl.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Value("${spring.application.version:unknown}")
    private String version;

    @Bean
    public OpenAPI openApiConfiguration() {
        return new OpenAPI()
            .info(getInfo());
    }

    private Info getInfo() {
        return new Info()
            .title("JMP REST API")
            .description("This is the REST API documentation for the JMP app.")
            .version(version);
    }
}