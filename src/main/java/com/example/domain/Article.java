package com.example.domain;

import jakarta.persistence.*;

import java.util.List;

/**
 * 記事情報を表すドメイン.
 */
@Entity
@Table(name = "articles")
public class Article {
    /** 記事id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** 投稿者名 */
    private String name;

    /** 内容 */
    private String content;

    /** コメントリスト */
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> commentList;



    public Article() {}

    public Article(int id, String name, String content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }


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

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }



    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", commentList=" + commentList +
                '}';
    }
}