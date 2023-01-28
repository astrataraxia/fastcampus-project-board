package com.fastcampus.projectboard.service;


import com.fastcampus.projectboard.domain.type.SearchType;
import com.fastcampus.projectboard.dto.ArticleDto;
import com.fastcampus.projectboard.dto.ArticleUpdateDto;
import com.fastcampus.projectboard.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArticleService {

    private final ArticleRepository articleRepository;

    public Page<ArticleDto> searchArticles(SearchType title, String searchKeyword) {
        return Page.empty();
    }

    public ArticleDto searchArticle(long l) {
        return null;
    }

    @Transactional
    public void saveArticle(ArticleDto dto) {
    }

    @Transactional
    public void updateArticle(long articleId, ArticleUpdateDto dto) {
    }

    @Transactional
    public void deleteArticle(long articleId) {
    }
}
