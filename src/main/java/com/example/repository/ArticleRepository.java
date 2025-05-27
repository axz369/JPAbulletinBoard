// com.example.repository.ArticleRepository
package com.example.repository;

import com.example.domain.Article;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

    @Query("SELECT a FROM Article a LEFT JOIN FETCH a.commentList ORDER BY a.id DESC")
    List<Article> findAllWithComments();
}

