package by.artezio.cloud.publishing.dto;

import javax.validation.constraints.NotNull;

/**
 * Класс-сущность логин-формы.
 *
 * @author Sergeev Evgeniy
 */
public class LoginForm {

    @NotNull(message = "email.empty")
    private String email;
    @NotNull(message = "password.empty")
    private String password;

    /**
     * Устанавливает значение электронной почты.
     *
     * @param email новый адрес электронной почты
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * Устанавливает значение пароля.
     *
     * @param password пароль
     */
    public void setPassword(final String password) {
        this.password = password;
    }

    /**
     *
     * @return значение email, полученной с формы
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @return значение password, полученной с формы
     */
    public String getPassword() {
        return password;
    }
}
