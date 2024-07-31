package com.example.exercise15.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class NewsArticle {



    @NotNull(message = "id should be not null")
    private int id;

    @NotEmpty(message = "title should be not empty")
    @Size(max = 100 , message = "Title should be less than or equal 100 characters")
    private String title;


    @NotEmpty(message = "content should be not empty")
    @Size(min = 5 , max = 20 , message = "Title should be less than or equal 100 characters")
    private String author;

    @NotEmpty(message = "content should be not empty")
    @Size(min = 201 , message = "content should be more than 200 characters")
    private String content;

    @NotEmpty(message = "category should be not empty")
    @Pattern(regexp = "^(politics|sports|technology)$" , message = "category should be either politics or sports or technology")
    private String category;

    @NotEmpty(message = "image URL should be not Null")
    private String imageUrl;

    @AssertFalse(message = "publish date should be initialize false")
    private boolean isPublished;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate publishDate;


}
