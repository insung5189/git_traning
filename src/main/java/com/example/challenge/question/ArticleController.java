package com.example.challenge.question;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleRepository articleRepository;
    private final ArticleService articleService;

    @GetMapping("/test")
    @ResponseBody
    public String test () {
        return "리스폰스바디 테스트";
    }


    @GetMapping("/list")
    public String articleList (Model model) {
        List<Article> articleList = this.articleService.getList();
        model.addAttribute("articleList", articleList);

        return "article_list";
    }

    @GetMapping("/create")
    public String articleCreate (ArticleForm articleForm) {
        return "article_form";
    }

    @PostMapping("/create")
    public String articleCreatePost (ArticleForm articleForm) {
        this.articleService.create(articleForm.getSubject(), articleForm.getContent());
        return "redirect:/article/list";
    }

    @GetMapping("/detail/{id}")
    public String articleDetail (@PathVariable Integer id, Model model) {
        Article article = this.articleService.getArticle(id);
        model.addAttribute("article", article);
        return "article_detail";
    }

    @GetMapping("/modify/{id}")
    public String articleModify (ArticleForm articleForm, @PathVariable("id") Integer id) {
        Article article = this.articleService.getArticle(id);
        articleForm.setSubject(article.getSubject());
        articleForm.setContent(article.getContent());

        return "article_form";
    }

    @PostMapping("/modify/{id}")
    public String articleModifyPost (ArticleForm articleForm, @PathVariable("id") Integer id) {
        Article article = this.articleService.getArticle(id);
        this.articleService.modify(article, articleForm.getSubject(), articleForm.getContent());
        return "redirect:/article/list";
    }

    @GetMapping("/delete/{id}")
    public String articleDelete(@PathVariable("id") Integer id) {
        Article article = this.articleService.getArticle(id);
        this.articleService.delete(article);
        return "redirect:/";
    }

}
