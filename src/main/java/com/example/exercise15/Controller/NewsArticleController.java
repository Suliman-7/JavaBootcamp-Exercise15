package com.example.exercise15.Controller;

import com.example.exercise15.Api.ApiResponse;
import com.example.exercise15.Model.NewsArticle;
import com.example.exercise15.Service.NewsArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v4/newsarticle")
@RequiredArgsConstructor
public class NewsArticleController {

    private final NewsArticleService newsArticleService;

    @GetMapping("/get")
    public ResponseEntity getNewsArticle(){

        ArrayList<NewsArticle> getnews = newsArticleService.getAllNewsArticles();
        return ResponseEntity.status(200).body(getnews);
    }

    @PostMapping("/post")
    public ResponseEntity postNewsArticle(@Valid  @RequestBody NewsArticle newsArticle , Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        newsArticleService.addNewsArticle(newsArticle);
        return ResponseEntity.status(200).body(new ApiResponse("news Article added successfully !"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateNewsArticle(@PathVariable int id , @Valid @RequestBody NewsArticle newsArticle, Errors errors ){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean update = newsArticleService.updateNewsArticle(id,newsArticle);
        if(update){
            return ResponseEntity.status(200).body(new ApiResponse("news Article updated successfully !"));
        }
        return ResponseEntity.status(400).body("news Article not found !");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteNewsArticle(@PathVariable int id){
        boolean delete = newsArticleService.deleteNewsArticle(id);

        if(delete){
            return ResponseEntity.status(200).body("news Article deleted successfully !");
        }
        return ResponseEntity.status(404).body("news Article not found !");
    }

    @PutMapping("/publish/{id}")
    public ResponseEntity publishNewsArticle(@PathVariable int id){
        boolean publish = newsArticleService.publishNewsArticle(id);
        if(publish){
            return ResponseEntity.status(200).body("news Article published successfully !");
        }
        return ResponseEntity.status(404).body("news Article not found !");
    }

    @GetMapping("/getpublished")
    public ResponseEntity getPublishedNewsArticle(){
        ArrayList<NewsArticle> published = newsArticleService.getAllPublishedNewsArticles();
        return ResponseEntity.status(200).body(published);
    }

    @GetMapping("/bycategory/{category}")
    public ResponseEntity getNewsArticleByCategory(@PathVariable String category){
        ArrayList<NewsArticle> byCategory = newsArticleService.getNewsArticlesByCategory(category);
        return ResponseEntity.status(200).body(byCategory);
    }





}
