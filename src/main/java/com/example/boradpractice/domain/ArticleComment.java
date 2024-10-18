package com.example.boradpractice.domain;

import java.time.LocalDateTime;

public class ArticleComment {
    private Long id;
    private ArticleComment article;
    private String title;
    private String content;
    private String hashtag;

    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime modifiedAt;
    private String modifiedBy;

}
