package com.wilinskiw.universal.user_management.service;

import com.wilinskiw.porfolio.formula_data.model.User;
import com.wilinskiw.porfolio.formula_data.service.UserDataService;
import com.wilinskiw.universal.user_management.dto.UserInfoDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class AuthService {
    private static final Logger log = LoggerFactory.getLogger(AuthService.class);
    private final UserDataService userDataService;

    public AuthService(UserDataService userDataService) {
        this.userDataService = userDataService;
    }

    public void registerUser(UserInfoDto userInfoDto) {
        String email = userInfoDto.email();
        String password = userInfoDto.password();

        if(!userDataService.emailExists(email)) {
            userDataService.save(new User(email, password));
            log.info("User with email {} registered", email);
        }
        else {
            log.info("User with email {} already exists", email);
        }
    }

}
