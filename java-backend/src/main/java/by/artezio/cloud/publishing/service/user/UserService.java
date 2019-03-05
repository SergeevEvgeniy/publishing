package by.artezio.cloud.publishing.service.user;

import by.artezio.cloud.publishing.dao.user.UserDao;
import by.artezio.cloud.publishing.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public User getUser(String email, String password) {
        //захэшировать пароль
        return userDao.getUserByLoginPass(email, password);
    }
}
