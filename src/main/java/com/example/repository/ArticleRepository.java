package com.example.repository;

import com.example.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//JpaRepository<Article, Integer> を継承することで、基本的な操作（find, save, delete など）が自動で提供される
//第一引数：対象エンティティ（Article）
//第二引数：主キーの型（Integer）
@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

    //**************************************
    //なぜinterface?
    //**************************************
}

