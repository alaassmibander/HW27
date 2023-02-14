package com.example.hw27.repo;

import com.example.hw27.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyUserRepo extends JpaRepository<MyUser,Integer> {
    MyUser findMyUserByUsername(String username);
    MyUser findMyUserById(Integer id);

}
