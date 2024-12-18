package com.example.boardpractice.dto.request;

import com.example.boardpractice.domain.Article;
import com.example.boardpractice.dto.ArticleCommentDto;
import com.example.boardpractice.dto.UserAccountDto;

public record ArticleCommentRequest(Long articleId, String content) {

    public static ArticleCommentRequest of(Long articleId, String content) {
        return new ArticleCommentRequest(articleId, content);
    }

    public ArticleCommentDto toDto(UserAccountDto userAccountDto) {
        return ArticleCommentDto.of(
                articleId,
                userAccountDto,
                content
        );
    }

}
