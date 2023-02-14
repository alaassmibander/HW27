package com.example.hw27.controller;

import com.example.hw27.dto.ApiResponse;
import com.example.hw27.model.Blog;
import com.example.hw27.model.MyUser;
import com.example.hw27.service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/blog")
public class blogController {

    private final BlogService blogService;

    // Admin
    @GetMapping("/all-blogs")
    public ResponseEntity getAllBlogs() {
        return ResponseEntity.status(200).body(blogService.getAllBlogs());
    }

    // User
    @GetMapping("/my-blogs")
    public ResponseEntity getMyBlogs(@AuthenticationPrincipal MyUser myUser) {
        return ResponseEntity.status(200).body(blogService.getMyBlogs(myUser.getId()));
    }

    // User
    @GetMapping("/{id}")
    public ResponseEntity getBlogById(@PathVariable Integer id, @AuthenticationPrincipal MyUser myUser) {
        return ResponseEntity.status(200).body(blogService.getBlogById(id, myUser.getId()));
    }

    @GetMapping("/{title}")
    public ResponseEntity getBlogByTitle(@PathVariable String title, @AuthenticationPrincipal MyUser myUser) {
        return ResponseEntity.status(200).body(blogService.getBlogByTitle(title, myUser.getId()));
    }

    // User
    @PostMapping("/add-blog")
    public ResponseEntity addBlog(@RequestBody @Valid Blog blog, @AuthenticationPrincipal MyUser myUser) {
        blogService.addBlog(blog, myUser.getId());
        return ResponseEntity.status(201).body(new ApiResponse("blog Added"));
    }

    // User
    @PutMapping("/update/{id}")
    public ResponseEntity updateBlog(@RequestBody @Valid Blog blog, @PathVariable Integer id, @AuthenticationPrincipal MyUser myUser) {
        blogService.updateBlog(id, blog, myUser.getId());
        return ResponseEntity.status(200).body(new ApiResponse("blog Updated"));
    }

    // User
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBlog(@PathVariable Integer id, @AuthenticationPrincipal MyUser myUser) {
        blogService.deleteBlog(id, myUser.getId());
        return ResponseEntity.status(200).body(new ApiResponse("blog deleted"));
    }

}
