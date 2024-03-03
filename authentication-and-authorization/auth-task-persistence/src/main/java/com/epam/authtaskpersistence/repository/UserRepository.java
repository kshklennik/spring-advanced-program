package com.epam.authtaskpersistence.repository;

import com.epam.authtaskpersistence.entity.AppUser;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<AppUser, Long> {

    AppUser findUserByUsername(String username);
}