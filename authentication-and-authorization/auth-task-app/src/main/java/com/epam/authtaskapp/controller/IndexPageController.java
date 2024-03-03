/*
 * (c) 2020 EPAM systems, Inc. All Rights Reserved. All information contained herein is, and remains the
 * property of EPAM Systems, Inc. and/or its suppliers and is protected by international intellectual
 * property law. Dissemination of this information or reproduction of this material is strictly forbidden,
 * unless prior written permission is obtained from EPAM Systems, Inc
 */

package com.epam.authtaskapp.controller;

import com.epam.authtaskapp.entity.ViewName;
import com.epam.authtaskbruteforce.service.LoginAttemptService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class IndexPageController {

    private final LoginAttemptService loginAttemptService;

    @Value("${spring.application.version:unknown}")
    private String version;

    @GetMapping("/index")
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName(ViewName.INDEX.getName());

        modelAndView.addObject("version", version);
        return modelAndView;
    }

    @GetMapping("/blocked-users")
    public ModelAndView blockedUsers(ModelAndView modelAndView) {
        modelAndView.setViewName(ViewName.BLOCKED_USERS.getName());

        modelAndView.addObject("blocked", loginAttemptService.getBlocked());
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView login(@RequestParam Optional<String> error) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(ViewName.LOGIN.getName());
        modelAndView.addObject("version", version);

        if (error.isPresent()) {
            modelAndView.addObject("error", error);
        }

        return modelAndView;
    }
}