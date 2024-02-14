/*
 * (c) 2024 EPAM systems, Inc. All Rights Reserved. All information contained herein is, and remains the
 * property of EPAM Systems, Inc. and/or its suppliers and is protected by international intellectual
 * property law. Dissemination of this information or reproduction of this material is strictly forbidden,
 * unless prior written permission is obtained from EPAM Systems, Inc
 */

package com.epam.conditionalconfigurationtask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableJpaRepositories
@ComponentScan("com.epam.conditionalconfigurationtask.*")
@SpringBootApplication
public class Application {

    public static void main(String... args) {
        log.info("STARTING SPRING FOUNDATION TASK 2 APP");
        SpringApplication.run(Application.class, args);
    }
}