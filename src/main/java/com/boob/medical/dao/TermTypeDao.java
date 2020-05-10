package com.boob.medical.dao;

import com.boob.medical.entity.TermType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TermTypeDao extends JpaRepository<TermType, Long>, JpaSpecificationExecutor<TermType> {

}
