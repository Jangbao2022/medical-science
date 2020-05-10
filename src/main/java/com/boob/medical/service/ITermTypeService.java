package com.boob.medical.service;


import com.boob.medical.dto.PageDto;
import com.boob.medical.entity.TermType;

import java.util.List;

public interface ITermTypeService {

    PageDto<TermType> getTermPage(int page, int size);

    List<TermType> getAllTermType();

    TermType getTermTypeById(Long termTypeId);

    boolean updateTermType(TermType termType);

    boolean insertTermType(TermType termType);

    boolean updateTermTypeByName(String oldName, String newName);

    boolean deleteTermTypeById(Long termTypeId);

    List<TermType> getTermTypesByKeyWord(String keyWord);
}
