/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.artezio.cloud.publishing.web.security;

import by.artezio.cloud.publishing.dto.AuthenticationResult;
import by.artezio.cloud.publishing.dto.LoginForm;
import by.artezio.cloud.publishing.dto.User;

/**
 *
 * @author Sergeev Evgeniy
 */
public interface SecurityService {

    /**
     * Возвращает объект {@link User}, соответсвующий текущего авторизованному
     * пользователю. Если пользователь не авторизирован(аноним), то возвращается
     * <code>null</code>
     *
     * @return объект {@link User}, либо <code>null</code>
     */
    User getCurrentUser();

    /**
     * Получает пользователя из сервиса и ложит его в сессию.
     *
     * @param loginForm форма логина с полями логин/пароль
     * @return AuthenticationResult результат аутентификации пользователя с
     * соответсвтующим сообщением
     */
    AuthenticationResult loginUser(LoginForm loginForm);

    /**
     * Проверяет, имеет ли залогиннеый сейчас пользователь роль "Редактор".
     *
     * @throws AccessDeniedException
     */
    void checkIsEditor();

    /**
     * Проверяет, имеет ли залогиннеый сейчас пользователь роль "Журналист".
     *
     * @throws AccessDeniedException
     */
    void checkIsJournalist();

    /**
     * Проверяет, имеет ли залогиннеый сейчас пользователь роль "Главный
     * Редактор".
     *
     * @throws AccessDeniedException
     */
    void checkIsChiefEditor();

}
