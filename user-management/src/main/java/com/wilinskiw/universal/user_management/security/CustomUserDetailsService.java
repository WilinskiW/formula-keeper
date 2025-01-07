package com.wilinskiw.universal.user_management.security;

import com.wilinskiw.porfolio.formula_data.model.User;
import com.wilinskiw.porfolio.formula_data.service.UserDataService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserDataService userDataService;

    public CustomUserDetailsService(UserDataService userDataService) {
        super();
        this.userDataService = userDataService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //username == email
        Optional<User> validationUser = userDataService.findUserByEmail(username);
        if (validationUser.isPresent()) {
            User user = validationUser.get();
            return null; //todo
        }
        throw new UsernameNotFoundException("Email or password not found");
    }

    public Collection<? extends GrantedAuthority> authorities() {
        return List.of(new SimpleGrantedAuthority("USER"));
    }


}
