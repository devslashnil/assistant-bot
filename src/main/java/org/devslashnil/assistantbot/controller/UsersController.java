package org.devslashnil.assistantbot.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("${app.http.bot}")
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class UsersController {

    private final UserService userService;

    /**
     * Return list of users and their plans
     */
    @GetMapping(path = "/users_idea")
    public List<User> getIdeaList() {
        log.debug("Method - getIdeaList was  called");
        return userService.getUserList();
    }
}
