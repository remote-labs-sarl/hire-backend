package com.remotelabs.hire.controllers;

import com.remotelabs.hire.BaseIntegrationIT;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserControllerIT extends BaseIntegrationIT {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void generatePassword() {

        String encodedPassword = passwordEncoder.encode("RemoteLabs@2023");
        System.out.println(encodedPassword);
    }
}
