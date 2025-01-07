package com.example.boardpractice.controller;

import com.example.boardpractice.config.TestSecurityConfig;
import com.example.boardpractice.service.ArticleService;
import com.example.boardpractice.service.PaginationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@DisplayName("View 컨트롤러 - 인증")
@Import(TestSecurityConfig.class)
@WebMvcTest(AuthControllerTest.EmptyController.class)
class AuthControllerTest {
    private final MockMvc mvc;

    @MockBean private ArticleService articleService;
    @MockBean private PaginationService paginationService;

    AuthControllerTest(@Autowired MockMvc mvc) {this.mvc = mvc;}

    @DisplayName("[view][GET] 로그인 페이지 - 정상호출")
    @Test
    public void givenNothing_whenTryingToLogIn_thenReturnsLogInView() throws Exception {

        mvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML));

        then(articleService).shouldHaveNoInteractions();
        then(paginationService).shouldHaveNoInteractions();
    }

    @TestComponent
    static class EmptyController {}

}
