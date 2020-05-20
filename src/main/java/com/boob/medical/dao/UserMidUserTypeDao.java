package com.boob.medical.dao;

import com.boob.medical.entity.Term;
import com.boob.medical.entity.UserMidUserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserMidUserTypeDao extends JpaRepository<UserMidUserType, Long>, JpaSpecificationExecutor<Term> {

}
