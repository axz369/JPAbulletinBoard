package com.example.domain;

import jakarta.persistence.*;

/**
 * コメント情報を表すドメイン.
 */
@Entity
@Table(name = "comments")
public class Comment {

    /** コメントid */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** コメントid */
    private String name;

    /** 投稿者名 */
    private String content;

    /** 記事 */
    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;



    // getter setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }



    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", article=" + article +
                '}';
    }
}