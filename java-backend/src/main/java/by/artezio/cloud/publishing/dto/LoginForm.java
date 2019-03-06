package by.artezio.cloud.publishing.dto;

/**
 * Класс-сущность логин-формы.
 *
 * @author Sergeev Evgeniy
 */
public class LoginForm {

    private String email;
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
