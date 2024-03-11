/*
 * (c) 2020 EPAM systems, Inc. All Rights Reserved. All information contained herein is, and remains the
 * property of EPAM Systems, Inc. and/or its suppliers and is protected by international intellectual
 * property law. Dissemination of this information or reproduction of this material is strictly forbidden,
 * unless prior written permission is obtained from EPAM Systems, Inc
 */

package com.epam.authtaskapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ViewName {

    INDEX("index"),
    LOGIN("login"),
    INFO("info"),
    BLOCKED_USERS("blocked-users"),
    DEFAULT_ERROR_VIEW("error");

    private final String name;
}
