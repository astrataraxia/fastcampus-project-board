package com.fastcampus.projectboard.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;


@Getter
@ToString
@Table(indexes= {
        @Index(columnList = "content"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ArticleComment extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false) @Setter private Article article;  //게시글  ID
    @Setter @Column(nullable = false, length = 500) private String content;  // 본문

    private ArticleComment(Article article, String content) {
        this.article = article;
        this.content = content;
    }

    public static ArticleComment of(Article article, String content) {
        return new ArticleComment(article, content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArticleComment that)) return false;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
