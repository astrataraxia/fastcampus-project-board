package com.fastcampus.projectboard.service;

import com.fastcampus.projectboard.domain.Article;
import com.fastcampus.projectboard.domain.ArticleComment;
import com.fastcampus.projectboard.domain.type.SearchType;
import com.fastcampus.projectboard.dto.ArticleCommentDto;
import com.fastcampus.projectboard.dto.ArticleDto;
import com.fastcampus.projectboard.repository.ArticleCommentRepository;
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
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

@DisplayName("비지닛스 로직 - 게시글")
@ExtendWith(MockitoExtension.class)
class ArticleCommentServiceTest {

    @InjectMocks private ArticleCommentService sut;
    @Mock private ArticleRepository articleRepository;
    @Mock private ArticleCommentRepository articleCommentRepository;


    @Test
    @DisplayName("게시글 Id로 검색하면, 해당하는 게시글의 댓글 리스트를 반환한다.")
    void givenArticleId_WhenSearchingArticleComments_thenReturnsArticleCommentList() {
        //given
        Long articleId = 1L;
        Optional<Article> article = Optional.of(Article.of("title", "content", "hashtag"));

        given(articleRepository.findById(articleId)).willReturn(article);
        // when
        List<ArticleCommentDto> articleComments =  sut.searchArticleComment(1L);

        // then
        assertThat(articleComments).isNotNull();
        then(articleRepository).should().findById(articleId);
    }

    @Test
    @DisplayName("댓글 정보를 입력하면 댓글을 저장한다.")
    void givenArticleCommentInfo_whenSavingArticleComment_thenSavesArticleComment() {
        // Given
        given(articleCommentRepository.save(any(ArticleComment.class))).willReturn(null);

        // When
        sut.saveArticleComment(ArticleCommentDto.of(LocalDateTime.now(), "Uno", LocalDateTime.now(), "Uno", "comment"));

        // Then
        then(articleCommentRepository).should().save(any(ArticleComment.class));
    }
}