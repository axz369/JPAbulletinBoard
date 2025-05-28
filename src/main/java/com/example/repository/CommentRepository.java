// com.example.repository.CommentRepository
package com.example.repository;

import com.example.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// JpaRepository<Article, Integer> を継承することで、基本的な操作（find, save, delete など）が自動で提供される
// 第一引数：対象エンティティ（Comment）
// 第二引数：主キーの型（Integer）
@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    // クエリメソッド（Query Methods）: メソッド名から自動的にクエリを解析して実行する
    // 記事idから付随するコメントを取得する
    List<Comment> findByArticleIdOrderByIdAsc(int articleId);
}
