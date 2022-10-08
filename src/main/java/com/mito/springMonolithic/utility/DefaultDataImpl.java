package com.mito.loginDeneme.utility;

import com.mito.loginDeneme.repository.entity.Gender;
import com.mito.loginDeneme.repository.entity.User;
import com.mito.loginDeneme.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor
@Component
public class DefaultDataImpl {
    private final UserService userService;
    @PostConstruct
    private void create(){
        saveUser();
    }

    private void saveUser() {
        User user = User.builder()
                .username("admin")
                .password("admin")
                .email("admin@admin")
                .gender(Gender.ERKEK)
                .build();
        userService.save(user);
    }
}
