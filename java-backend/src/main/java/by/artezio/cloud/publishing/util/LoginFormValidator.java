package by.artezio.cloud.publishing.util;

import by.artezio.cloud.publishing.dto.LoginForm;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Валидатор для Login-формы.
 *
 * @author Sergeev Evgeniy
 */
public class LoginFormValidator implements Validator {

    @Override
    public boolean supports(final Class clazz) {
        return LoginForm.class.equals(clazz);
    }

    @Override
    public void validate(final Object o, final Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "email", "email.empty");
        ValidationUtils.rejectIfEmpty(errors, "password", "password.empty");
        LoginForm lf = (LoginForm) o;
//        if (lf.getEmail().length() == 0) {
//            errors.rejectValue("email", "empty_field");
//        } else if (p.getAge() > 110) {
//            e.rejectValue("age", "too.darn.old");
//        }
    }

}