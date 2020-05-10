package com.boob.medical.dao;

import com.boob.medical.entity.Term;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TermDao extends JpaRepository<Term, Long>, JpaSpecificationExecutor<Term> {

}
