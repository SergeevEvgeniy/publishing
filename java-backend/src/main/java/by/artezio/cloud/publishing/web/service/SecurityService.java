/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.artezio.cloud.publishing.web.service;

import by.artezio.cloud.publishing.dto.AuthenticationResult;
import by.artezio.cloud.publishing.dto.LoginForm;
import by.artezio.cloud.publishing.dto.User;

/**
 *
 * @author Sergeev Evgeniy
 */
public interface SecurityService {

    /**
     *
     * @return User dto
     */
    User getCurrentUser();

    /**
     *
     * @param loginForm форма логина с полями логин/пароль
     * @return AuthenticationResult dto
     */
    AuthenticationResult loginUser(LoginForm loginForm);
}


