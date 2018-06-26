package com.elina.railwayApp.service.Implementation;

import com.elina.railwayApp.model.User;
import com.elina.railwayApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.findByEmail(s);
        if (user != null) {
            Collection<GrantedAuthority> authorities = user.getRoleSet()
                    .stream()
                    .map(userRole-> new SimpleGrantedAuthority(userRole.getType()))
                    .collect(Collectors.toCollection(ArrayList::new));
            return new org.springframework.security.core.userdetails
                    .User(s,user.getPassword(),true, true, true, true,authorities);
        } else throw new UsernameNotFoundException("User not found!");
    }
}

