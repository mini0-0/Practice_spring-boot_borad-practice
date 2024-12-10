package com.example.boardpractice.repository;

import com.example.boardpractice.config.JpaConfig;
import com.example.boardpractice.domain.Article;
import com.example.boardpractice.domain.UserAccount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("JPA 연결 테스트")
@Import(JpaConfig.class)
@DataJpaTest
class JpaRepositoryTest {

    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;
    private final UserAccountRepository userAccountRepository;

    JpaRepositoryTest(
            @Autowired ArticleRepository articleRepository,
            @Autowired ArticleCommentRepository articleCommentRepository,
            @Autowired UserAccountRepository userAccountRepository
    ) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
        this.userAccountRepository = userAccountRepository;
    }

    @DisplayName("select 테스트")
    @Test
    void givenTestData_whenSelecting_thenWorksFine() {
        // Given

        // When
        List<Article> articles = articleRepository.findAll();

        // Then
        assertThat(articles)
                .isNotNull()
                .hasSize(0); // classpath:resources/data.sql 참조
    }

    @DisplayName("insert 테스트")
    @Test
    void givenTestData_whenInserting_thenWorksFine() {
        // Given
        long previousCount = articleCommentRepository.count();
        UserAccount userAccount = userAccountRepository.save(UserAccount.of("mini","pw",null,null,null));
        Article article = Article.of(userAccount, "new article", "new content", "#spring");

        // When
        articleRepository.save(article);

        // Then
        assertThat(articleRepository.count()).isEqualTo(previousCount + 1);
    }

    @DisplayName("update 테스트")
    @Test
    void givenTestData_whenUpdating_thenWorksFine() {
        // Given
        Article article = articleRepository.findById(1L).orElseThrow();
        String updatedHashtag = "#SpringBoot";
        article.setHashtag(updatedHashtag);

        // When
        Article savedArticle = articleRepository.saveAndFlush(article);

        // Then
        assertThat(savedArticle).hasFieldOrPropertyWithValue("hashtag", updatedHashtag);
    }

    @DisplayName("delete 테스트")
    @Test
    void givenTestData_whenDeleting_thenWorksFine() {
        // Given
        Article article = articleRepository.findById(1L).orElseThrow();
        long previousArticleCount = articleRepository.count();
        long previousArticleCommentCount = articleCommentRepository.count();
        int deletedCommentsSize = article.getArticleComments().size();

        // When
        articleRepository.delete(article);

        // Then
        assertThat(articleRepository.count()).isEqualTo(previousArticleCount - 1);
        assertThat(articleCommentRepository.count()).isEqualTo(previousArticleCommentCount - deletedCommentsSize);
    }


//    @DisplayName("update 테스트")
//    @Test
//    void givenTestData_whenUpdating_thenWorksFine() {
//        // Given
//        Article savedArticle = articleRepository.save(Article.of("new article", "new content", "#spring"));
//        Long articleId = savedArticle.getId(); // 저장된 id 가져오기
//        String updatedHashtag = "#SpringBoot";
//
//        // When
//        Article article = articleRepository.findById(articleId).orElseThrow();
//        article.setHashtag(updatedHashtag);
//        Article updatedArticle = articleRepository.saveAndFlush(article);
//
//        // Then
//        assertThat(updatedArticle).hasFieldOrPropertyWithValue("hashtag", updatedHashtag);
//    }
//
//    @DisplayName("delete 테스트")
//    @Test
//    void givenTestData_whenDeleting_thenWorksFine() {
//        // Given
//        Article savedArticle = articleRepository.save(Article.of("new article", "new content", "#spring"));
//        Long articleId = savedArticle.getId(); // 저장된 id 가져오기
//        long previousArticleCount = articleRepository.count();
//        long previousArticleCommentCount = articleCommentRepository.count();
//        int deletedCommentsSize = savedArticle.getArticleComments().size();
//
//        // When
//        Article article = articleRepository.findById(articleId).orElseThrow();
//        articleRepository.delete(article);
//
//        // Then
//        assertThat(articleRepository.count()).isEqualTo(previousArticleCount - 1);
//        assertThat(articleCommentRepository.count()).isEqualTo(previousArticleCommentCount - deletedCommentsSize);
//    }

}