package by.artezio.cloud.publishing.util.validator;

import by.artezio.cloud.publishing.domain.LoginForm;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class LoginFormValidator implements Validator {

    @Override
    public boolean supports(Class clazz) {
        return LoginForm.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
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
