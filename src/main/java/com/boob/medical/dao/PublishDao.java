package com.boob.medical.dao;

import com.boob.medical.entity.Publish;
import com.boob.medical.entity.Term;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PublishDao extends JpaRepository<Publish, Long>, JpaSpecificationExecutor<Term> {

}
