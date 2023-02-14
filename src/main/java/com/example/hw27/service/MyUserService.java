package com.example.hw27.service;

import com.example.hw27.Exception.ApiException;
import com.example.hw27.model.MyUser;
import com.example.hw27.repo.MyUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyUserService {

    private final MyUserRepo myUserRepo;


    public List<MyUser> getAllUsers(){
        return myUserRepo.findAll();
    }

    public MyUser getUser(Integer id){
        MyUser myUser=myUserRepo.findMyUserById(id);
        if (myUser==null){
            throw new ApiException("User Not Found!");
        }
        return myUser;
    }


    public void addUser(MyUser newUser){
        newUser.setRole("USER");
        String hashedPassword=new BCryptPasswordEncoder().encode(newUser.getPassword());
        newUser.setPassword(hashedPassword);
        myUserRepo.save(newUser);
    }

    public void deleteUser(Integer id){
        MyUser myUser=myUserRepo.findMyUserById(id);
        if(myUser==null){
            throw new ApiException("User Not Found");
        }
        myUserRepo.delete(myUser);
    }


    public void updateUser(MyUser newUser, Integer id){
        MyUser oldUser=myUserRepo.findMyUserById(id);

        newUser.setId(id);
        newUser.setRole(oldUser.getRole());
        newUser.setPassword(new BCryptPasswordEncoder().encode(newUser.getPassword()));

        myUserRepo.save(newUser);
    }


}