package com.example.domain;

import jakarta.persistence.*;

import java.util.List;

//@Entity：このクラスがJPAエンティティであることを示す
//@Table(name = "articles")：このエンティティが articlesテーブルにマッピングされることを表す
/**
 * 記事情報を表すドメイン.
 */
@Entity
@Table(name = "articles")
public class Article {

    //@Id：このフィールドが主キーであることを示す
    //@GeneratedValue(strategy = GenerationType.IDENTITY)：DB側で自動採番されるという意味
    /** 記事id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** 投稿者名 */
    private String name;

    /** 内容 */
    private String content;

    //@OneToMany：この Article に複数の Comment が紐づく（1記事に対し複数コメント）関係 親のfindAllを使ったときに自動でとれるようになる
    //mappedBy = "article"：このクラス（親、Article）には多くの子（Comment）が紐づいていて、その紐づけは Comment クラスの article フィールドによって行われているという意味
    /** コメントリスト */
    @OneToMany(mappedBy = "article")
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