package com.example.boardpractice.config;

import com.example.boardpractice.domain.UserAccount;
import com.example.boardpractice.dto.UserAccountDto;
import com.example.boardpractice.repository.UserAccountRepository;
import com.example.boardpractice.service.UserAccountService;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@Import(SecurityConfig.class)
public class TestSecurityConfig {

    @MockBean private UserAccountService userAccountService;

    @BeforeTestMethod
    public void securitySetUp() {
        given(userAccountService.searchUser(anyString()))
                .willReturn(Optional.of(createUserAccountDto()));
        given(userAccountService.saveUser(anyString(), anyString(), anyString(), anyString(), anyString()))
                .willReturn(createUserAccountDto());

    }

    private UserAccountDto createUserAccountDto() {
        return UserAccountDto.of(
                "roseTest",
                "pw",
                "rose-test@email.com",
                "rose-test",
                "test memo"
        );
    }



}