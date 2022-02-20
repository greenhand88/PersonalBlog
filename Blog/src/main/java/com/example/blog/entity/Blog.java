package com.example.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@AllArgsConstructor
public class Blog {
    String account;
    List<String>articles;
}
