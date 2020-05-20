package com.boob.medical.service.impl;

import com.boob.medical.dao.UserDao;
import com.boob.medical.dao.UserMidUserTypeDao;
import com.boob.medical.dao.UserTypeDao;
import com.boob.medical.entity.User;
import com.boob.medical.entity.UserMidUserType;
import com.boob.medical.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * userService接口实现类
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserMidUserTypeDao userMidUserTypeDao;

    @Override
    public User checkUserLogin(User user) {
        if (user.getAccount() == null || user.getPassword() == null || user.getPower() == null) {
            return null;
        }
        List<User> users = userDao.findAll(Example.of(user));
        if (users.size() > 1) {
            throw new RuntimeException("用户数量出错,账号为" + user.getAccount() + ",该账号存在" + users.size() + "个用户");
        } else if (users.size() == 0) {
            return null;
        } else {
            return users.get(0);
        }
    }

    @Override
    public boolean checkUserRegister(User user) {
        if (user.getAccount() == null) {
            return false;
        }
        User dbUser = new User();
        dbUser.setAccount(user.getAccount());
        return userDao.count(Example.of(dbUser)) == 0;
    }

    @Override
    public void saveUser(User user) {
        user.setGmtCreated(new Date());
        user.setGmtModified(user.getGmtCreated());
        User save = userDao.save(user);
        userMidUserTypeDao.save(new UserMidUserType(null, save.getId(), (long) user.getPower()));

    }

    @Override
    public void updateUser(User user) {
        user.setGmtModified(new Date());
        userDao.save(user);
    }

    @Override
    public void deleteUserById(Long id) {

        userDao.deleteById(id);
    }

    @Override
    public User getUserById(Long id) {
        User user = userDao.getOne(id);
        return user;
    }
}
