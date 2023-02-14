package com.example.hw27.service;

import com.example.hw27.Exception.ApiException;
import com.example.hw27.model.Blog;
import com.example.hw27.model.MyUser;
import com.example.hw27.repo.MyUserRepo;
import com.example.hw27.repo.BlogRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepo blogRepo;
    private final MyUserRepo authRepo;

    public List getAllBlogs() {
        return blogRepo.findAll();
    }

    public void addBlog(Blog blog, Integer auth) {
        MyUser myUser = authRepo.findMyUserById(auth);
        blog.setMyUser(myUser);

        blogRepo.save(blog);
    }

    public void updateBlog(Integer id, Blog newBlog, Integer auth) {

        Blog oldBlog = blogRepo.findBlogById(id);
        MyUser myUser = authRepo.findMyUserById(auth);
        if (oldBlog == null) {
            throw new ApiException("Blog not found");
        } else if (oldBlog.getMyUser().getId() != auth) {
            throw new ApiException("This blog does not belong to you !");
        }

        newBlog.setId(id);
        newBlog.setMyUser(myUser);

        blogRepo.save(newBlog);
    }

    public void deleteBlog(Integer id, Integer auth) {
        Blog blog = blogRepo.findBlogById(id);
        if (blog == null) {
            throw new ApiException("blog not found");
        } else if (blog.getMyUser().getId() != auth) {
            throw new ApiException("This blog does not belong to you !");
        }

        blogRepo.delete(blog);
    }

    public Blog getBlogById(Integer id, Integer auth) {
        Blog blog = blogRepo.findBlogById(id);
        if (blog == null) {
            throw new ApiException("blog not Found");
        }
        if (blog.getMyUser().getId() != auth) {
            throw new ApiException("This blog does not belong to you !");
        }
        return blog;
    }

    public List getMyBlogs(Integer auth) {
        MyUser myUser = authRepo.findMyUserById(auth);
        List Blogs = blogRepo.findAllByMyUser(myUser);
        if (Blogs.isEmpty()) {
            throw new ApiException("you dont have any blog !");
        }
        return Blogs;

    }
    public Blog getBlogByTitle(String title, Integer auth) {
        Blog blog = blogRepo.findBlogByTitle(title);
        if (blog == null) {
            throw new ApiException("blog not Found");
        }
        if (blog.getMyUser().getId() != auth) {
            throw new ApiException("This blog does not belong to you !");
        }
        return blog;
    }

}
