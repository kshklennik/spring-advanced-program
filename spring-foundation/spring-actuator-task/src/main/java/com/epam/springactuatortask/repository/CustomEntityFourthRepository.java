/*
 * (c) 2024 EPAM systems, Inc. All Rights Reserved. All information contained herein is, and remains the
 * property of EPAM Systems, Inc. and/or its suppliers and is protected by international intellectual
 * property law. Dissemination of this information or reproduction of this material is strictly forbidden,
 * unless prior written permission is obtained from EPAM Systems, Inc
 */

package com.epam.springactuatortask.repository;

import com.epam.springactuatortask.entity.CustomEntityFourth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomEntityFourthRepository extends JpaRepository<CustomEntityFourth, Long> {
}