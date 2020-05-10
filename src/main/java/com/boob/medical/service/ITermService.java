package com.boob.medical.service;

import com.boob.medical.dto.PageDto;
import com.boob.medical.entity.Term;

import java.util.List;

/**
 * termService接口
 */
public interface ITermService {

    PageDto<Term> getTermPage(int page, int size);

    PageDto<Term> getTermPageByType(int page, int size, Long termTypeId);

    PageDto<Term> getTermPageByKeyWord(int page, int size, String keyWord);

    List<Term> getTermList(int size);

    Term getTermById(Long termId);

    Term updateTerm(Term term);

    Term insertTerm(Term term);

    boolean deleteTermById(Long termId);

}
