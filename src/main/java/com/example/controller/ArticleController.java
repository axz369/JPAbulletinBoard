package com.example.controller;

import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.form.ArticleForm;
import com.example.form.CommentForm;
import com.example.repository.ArticleRepository;
import com.example.repository.CommentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 記事情報を操作するコントローラ.
 */
@Controller
@RequestMapping("/article")
public class ArticleController {

    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;
    public ArticleController(ArticleRepository articleRepository, CommentRepository commentRepository){
        this.articleRepository = articleRepository;
        this.commentRepository = commentRepository;
    }



    /**
     * 記事一覧画面を表示する.
     *
     * @return 記事一覧と投稿画面
     */
    @GetMapping("")
    public String index(ArticleForm articleForm, CommentForm commentForm, Model model){

        //表示するたびに最新の記事一覧を取得

        //**************************************
        // domainで@ManyToOneや@OneToManyを書いているのでfindAll()のみでコメントまで付いてくる
        //**************************************

        List<Article> articleList = articleRepository.findAll();
        model.addAttribute("articleList",articleList);
        return "index";
    }



    /**
     * 記事を投稿する.
     *
     * @param articleForm 記事フォーム
     * @return 記事一覧画面
     */
    @PostMapping("/insert-article")
    public String insertArticle(@Validated ArticleForm articleForm, BindingResult articleResult, CommentForm commentForm,Model model){

        //一つでもエラーがあれば戻る
        if(articleResult.hasErrors()){
            return index(articleForm,commentForm,model);
        }

        Article article = new Article();

        //記事投稿者と内容を手動でマッピング
        article.setName(articleForm.getArticleName());
        article.setContent(articleForm.getArticleContent());

        //実行
        articleRepository.save(article);
        return "redirect:/article";
    }



    /**
     * コメントを投稿する.
     *
     * @param commentForm コメントフォーム
     * @return 記事一覧画面
     */
    @PostMapping("/insert-comment")
    public String insertComment(ArticleForm articleForm,
                                @Validated CommentForm commentForm,
                                BindingResult commentResult,
                                Model model){

        //一つでもエラーがあれば戻る
        if(commentResult.hasErrors()){
            return index(articleForm, commentForm, model);
        }

        Comment comment = new Comment();
        //articleIdのみ自動でマッピング
        BeanUtils.copyProperties(commentForm,comment);
        //コメント投稿者と内容を手動でマッピング
        comment.setName(commentForm.getCommentName());
        comment.setContent(commentForm.getCommentContent());


        //**************************************
        //commentドメインにはarticleオブジェクトをフィールドに持っているのでまず記事を取得する
        //.orElseThrow() : Optional に対して .orElseThrow() を呼ぶと、id に対応する Article が見つかればその中身が返り、なければ Optional.empty()」が返る
        //**************************************

        // コメントを紐づける対象の記事を取得
        Article article = articleRepository.findById(commentForm.getArticleId())
                .orElseThrow(); // IDが不正な場合は例外

        //コメントのarticleプロパティに記事をセット
        comment.setArticle(article);

        //実行
        commentRepository.save(comment);

        return "redirect:/article";
    }



    /**
     * 記事を削除する.
     *
     * @param articleId 記事id
     * @return 記事一覧画面
     */
    @PostMapping("/delete-article")
    public String deleteArticle(Integer articleId){
        articleRepository.deleteById(articleId);
        return "redirect:/article";
    }
}