package cn.itcast.bookstore.user.service;

import cn.itcast.bookstore.user.dao.UserDao;
import cn.itcast.bookstore.user.domain.User;

public class UserService {

    private UserDao userDao = new UserDao();

    public void regist(User form) throws UserException {
        if (userDao.findByEmail(form.getEmail()) != null) {
            throw new UserException("the email has been registed");
        }
        if (userDao.findByUsername(form.getUsername()) != null) {
            throw new UserException("the username has been registed");
        }
        userDao.add(form);
    }

    public User login(User form) throws UserException {
        User user = userDao.findByUsername(form.getUsername());
        if (user != null) {
            if (user.getPassword().equals(form.getPassword())) {
                return user;
            }
            else throw new UserException("password is wrong");
        }
        else throw new UserException("username is wrong");
    }
}
