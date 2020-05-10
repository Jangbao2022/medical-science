package com.boob.medical.service;

import com.boob.medical.entity.User;

/**
 * userService接口
 */
public interface IUserService {

    /**
     * 检查用户名密码能否登录
     *
     * @param user
     * @return 如果能登录, 返回数据库中的user, 否则返回空
     */
    User checkUserLogin(User user);

    /**
     * 检查用户名密码能否注册
     *
     * @param user
     * @return
     */
    boolean checkUserRegister(User user);

    /**
     * 注册用户
     *
     * @param user
     * @return
     */
    void saveUser(User user);

    /**
     * 更新用户
     *
     * @param user
     * @return
     */
    void updateUser(User user);

    /**
     * 根据id删除用户
     *
     * @param id
     * @return
     */
    void deleteUserById(Long id);

    /**
     * 根据Id查询用户
     *
     * @param id
     * @return
     */
    User getUserById(Long id);

}
