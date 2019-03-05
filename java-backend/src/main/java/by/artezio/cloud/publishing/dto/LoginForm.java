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
