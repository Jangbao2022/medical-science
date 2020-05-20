package com.boob.medical.dao;

import com.boob.medical.entity.User;
import com.boob.medical.entity.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTypeDao extends JpaRepository<UserType, Long> {

}
