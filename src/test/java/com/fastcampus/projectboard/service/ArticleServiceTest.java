package com.fastcampus.projectboard.service;

import com.fastcampus.projectboard.domain.Article;
import com.fastcampus.projectboard.domain.type.SearchType;
import com.fastcampus.projectboard.dto.ArticleDto;
import com.fastcampus.projectboard.dto.ArticleUpdateDto;
import com.fastcampus.projectboard.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

@DisplayName("비지닛스 로직 - 게시글")
@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @InjectMocks private ArticleService sut;
    @Mock private ArticleRepository articleRepository;

    @Test
    @DisplayName("게시글을 검색하면, 게시글 리스트를 반환한다.")
    void givenSearchParameters_WhenSearchingArticles_thenReturnsArticleList() {
        //given
        // when
        Page<ArticleDto> articles = sut.searchArticles(SearchType.TITLE , "search keyword");
        // then
        assertThat(articles).isNotNull();
    }

    @Test
    @DisplayName("게시글을 조회하면, 게시글을 반환한다.")
    void givenArticleId_WhenSearchingArticles_thenReturnsArtic() {
        //given
        // when
        ArticleDto articles = sut.searchArticle(1L);
        // then
        assertThat(articles).isNotNull();
    }

    @Test
    @DisplayName("게시글을 정보를 입력하면 게시글을 생성한다.")
    void givenArticleInfo_WhenSavingArticle_thenSaveArticle() {
        //given
        ArticleDto dto = ArticleDto.of(LocalDateTime.now(), "Uno", "title", "content", "#java");
        given(articleRepository.save(any(Article.class))).willReturn(null);
        // when
        sut.saveArticle(dto);
        // then
        then(articleRepository).should().save(any(Article.class));
    }

    @Test
    @DisplayName("게시글의 ID와 정보를 입력하면, 게시글을 수정한다.")
    void givenArticleIdandModifiedInfo_WhenSavingArticle_thenUpdateArticle() {
        //given
        ArticleUpdateDto dto = ArticleUpdateDto.of( "title", "content", "#java");
        given(articleRepository.save(any(Article.class))).willReturn(null);
        // when
        sut.updateArticle(1L, dto);
        // then
        then(articleRepository).should().save(any(Article.class));
    }

    @Test
    @DisplayName("게시글의 ID를 입력하면, 게시글을 삭제한다.")
    void givenArticleId_WhenDeletingArticle_thenDeleteArticle() {
        //given

        willDoNothing().given(articleRepository).delete(any(Article.class));
        // when
        sut.deleteArticle(1L);
        // then
        then(articleRepository).should().delete(any(Article.class));
    }

}