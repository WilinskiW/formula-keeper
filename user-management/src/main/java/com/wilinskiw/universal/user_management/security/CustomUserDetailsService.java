package com.wilinskiw.universal.user_management.security;

import com.wilinskiw.porfolio.formula_data.model.Role;
import com.wilinskiw.porfolio.formula_data.model.User;
import com.wilinskiw.porfolio.formula_data.service.UserDataService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getEmail())
                    .password(user.getPassword())
                    .authorities(appendRolesToStr(userDataService.findUserRolesByUser(user)))
                    .build();
        }
        throw new UsernameNotFoundException("Email or password not found");
    }

    private String appendRolesToStr(List<Role> roles){
       return roles.stream()
               .map(role -> role.getRole())
               .collect(Collectors.joining(", "));
    }
}
