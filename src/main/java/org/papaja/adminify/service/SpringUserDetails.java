package org.papaja.adminify.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SpringUserDetails implements UserDetailsService {

    @Autowired
    private UserService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = service.loadUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        System.out.println(user.getUsername());
        System.out.println(user.getPassword());

        return user;
    }

}
