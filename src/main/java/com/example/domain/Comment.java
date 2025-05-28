package com.example.domain;

import jakarta.persistence.*;

//@Entity：このクラスがJPAエンティティであること表す。データベースの1つのテーブルと対応する。
//@Table(name = "comments")：このエンティティが commentsテーブルにマッピングされることを表す
/**
 * コメント情報を表すドメイン.
 */
@Entity
@Table(name = "comments")
public class Comment {

    //@Id：このフィールドが主キーであることを示す
    //@GeneratedValue(strategy = GenerationType.IDENTITY)：DB側で自動採番されるという意味
    /** コメントid */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** コメントid */
    private String name;

    /** 投稿者名 */
    private String content;




    //**************************************
    //記事idではなく記事オブジェクトをフィールドに持つ方法で実装してもいいのか？
    //記事idにしてしまうと、JPAの機能がうまく使えず 「記事とコメントの関係」が複雑になり、実装が難しくなりそうだった
    //**************************************

    //@ManyToOne：このコメントが1つの記事（Article）に属していることを示す（多：1 の関係）
    //@JoinColumn(name = "article_id")：commentsテーブルのarticle_idカラムが外部キーとなり、article（親記事）と紐付けらるという意味
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