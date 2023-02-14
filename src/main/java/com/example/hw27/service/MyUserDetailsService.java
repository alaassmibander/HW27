package com.example.hw27.service;

import com.example.hw27.model.MyUser;
import com.example.hw27.repo.MyUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final MyUserRepo authRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser myUser = authRepo.findMyUserByUsername(username);
        if (myUser == null) {
            throw new UsernameNotFoundException("Wrong username or password");
        }
        return myUser;    }
}
