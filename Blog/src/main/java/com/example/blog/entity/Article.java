package com.example.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@AllArgsConstructor
public class Article {
    String id;
    String author;
    String title;
    Date uploadTime;
}
