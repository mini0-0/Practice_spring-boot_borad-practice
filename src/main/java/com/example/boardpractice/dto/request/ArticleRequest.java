package com.example.boardpractice.dto.request;

import com.example.boardpractice.domain.Article;
import com.example.boardpractice.dto.ArticleDto;
import com.example.boardpractice.dto.UserAccountDto;

public record ArticleRequest (
    String title,
    String content,
    String hashtag
) {
    public static ArticleRequest of(String title, String content, String hashtag) {
        return new ArticleRequest(title, content, hashtag);
    }

    public ArticleDto toDto(UserAccountDto userAccountDto) {
        return ArticleDto.of(
                userAccountDto,
                title,
                content,
                hashtag
        );
    }

}
