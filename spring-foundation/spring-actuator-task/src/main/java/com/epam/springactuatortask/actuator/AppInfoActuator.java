/*
 * (c) 2024 EPAM systems, Inc. All Rights Reserved. All information contained herein is, and remains the
 * property of EPAM Systems, Inc. and/or its suppliers and is protected by international intellectual
 * property law. Dissemination of this information or reproduction of this material is strictly forbidden,
 * unless prior written permission is obtained from EPAM Systems, Inc
 */

package com.epam.springactuatortask.actuator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Map;

import lombok.RequiredArgsConstructor;

@Component
@Endpoint(id = "appInfo")
@RequiredArgsConstructor
public class AppInfoActuator {

    private static final String KEY_PROFILE = "PROFILE";
    private static final String KEY_DB_URL = "DB_URL";
    private static final String DELIMITER = ",";
    private final Environment environment;

    @Value("${spring.datasource.jdbc-url}")
    private String dbUrl;

    @WriteOperation
    public Map<String, String> infoMap() {
        return Map.of(
            KEY_PROFILE, String.join(DELIMITER, environment.getActiveProfiles()),
            KEY_DB_URL, dbUrl
        );
    }
}