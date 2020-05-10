package com.boob.medical.dao;

import com.boob.medical.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void testFindAll() {

        List<User> list = userDao.findAll();
        for (User user : list) {
            System.out.println(user);
        }
    }

}
