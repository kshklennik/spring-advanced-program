package com.epam.cloudserviceimpl.service;

import com.epam.jmpdto.entity.User;
import com.epam.jmpserviceapi.service.UserService;

public interface UserServiceExtended extends UserService {

    User getUserById(Long userId);
}
