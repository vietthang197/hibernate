package com.thanglv.hibernate.services;

import com.thanglv.hibernate.entity.ApiInfo;
import com.thanglv.hibernate.entity.Authority;
import com.thanglv.hibernate.entity.User;
import com.thanglv.hibernate.repository.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author thanglv on 4/27/2021
 * @project hibernate
 */

@Component("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     *  function get user_info by username from database
     *
     * @param username
     * @return
     */
    @SneakyThrows
    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Set<GrantedAuthority> authorities = new HashSet<>();
            for (Authority authority : user.getAuthorities()) {
                for (ApiInfo apiInfo : authority.getApiInfos()) {
                    authorities.add(new SimpleGrantedAuthority(apiInfo.getAction()));
                }
            }
            return  new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
        } else
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
    }
}
