package com.example.hw27.repo;

import com.example.hw27.model.Blog;
import com.example.hw27.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepo extends JpaRepository<Blog, Integer> {
    Blog findBlogById(Integer id);

    List<Blog> findAllByMyUser(MyUser myUser);

    Blog findBlogByTitle(String title);
}
