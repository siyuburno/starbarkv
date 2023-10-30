package com.auth.service;

import com.auth.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class UserDetailServiceImpl implements UserDetailsService {

    private List<UserDTO> userList;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initData() {
        String password = passwordEncoder.encode("123456");

        userList = new ArrayList<>();
        UserDTO userExample1 = new UserDTO();
        userExample1.setId(1L);
        userExample1.setUsername("bruno");
        userExample1.setPassword(password);
        userExample1.setRoles(Arrays.asList(new SimpleGrantedAuthority("ADMIN")));
        userList.add(userExample1);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 通过UserDao查询用户信息，
        // username应该是用户唯一标识
        return userList.stream().filter(user -> user != null && username.equals(user.getUsername())).findFirst().map(userDTO -> {
            User userDetails = new User(userDTO.getUsername(), userDTO.getPassword(), userDTO.getRoles());
            return userDetails;
        }).orElseThrow(() -> new UsernameNotFoundException("用户" + username + "不存在"));
    }
}
