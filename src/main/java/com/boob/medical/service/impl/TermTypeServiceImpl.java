package com.boob.medical.service.impl;

import com.boob.medical.dao.TermDao;
import com.boob.medical.dao.TermTypeDao;
import com.boob.medical.dto.PageDto;
import com.boob.medical.entity.Term;
import com.boob.medical.entity.TermType;
import com.boob.medical.service.ITermTypeService;
import com.boob.medical.utils.SpecificationUtils;
import com.boob.medical.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class TermTypeServiceImpl implements ITermTypeService {

    @Autowired
    private TermTypeDao termTypeDao;

    @Autowired
    private TermDao termDao;

    @Override
    public PageDto<TermType> getTermPage(int page, int size) {
        PageDto<TermType> termPageDto = new PageDto<>("/term_type/page");
        long count = termTypeDao.count();
        termPageDto.init((int) count, page, size);
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<TermType> termPage = termTypeDao.findAll(pageable);

        List<TermType> termTypes = termPage.getContent();
        setCountToTermTypes(termTypes);
        termPageDto.setElements(termTypes);
        return termPageDto;
    }

    private void setCountToTermTypes(List<TermType> content) {
        List<TermType> termTypes = content;
        for (TermType termType : termTypes) {
            termType.setCount(countTermByTypeId(termType.getId()));
        }
    }

    private int countTermByTypeId(Long termTypeId) {
        int countTerm = (int) termDao.count(Example.of(new Term().setTermTypeId(termTypeId)));
        return countTerm;
    }

    @Override
    public List<TermType> getAllTermType() {
        List<TermType> termTypes = termTypeDao.findAll();
        setCountToTermTypes(termTypes);
        return termTypes;
    }

    @Override
    public List<TermType> getTermTypesByKeyWord(String keyWord) {
        String coreKeyWord = StringUtils.getKeyWordByRemoveSignal(keyWord, "#", "[", "]");
        Specification<TermType> specification = SpecificationUtils.getLikeSpecification("name", coreKeyWord);
        List<TermType> termTypes = termTypeDao.findAll(specification);
        setCountToTermTypes(termTypes);
        return termTypes;
    }


    @Override
    public TermType getTermTypeById(Long termTypeId) {
        TermType termType = termTypeDao.getOne(termTypeId);
        termType.setCount(countTermByTypeId(termType.getId()));
        return termType;
    }

    @Override
    public boolean updateTermType(TermType termType) {
        if (!checkTermType(termType)) {
            return false;
        }
        List<Term> terms = termDao.findAll(Example.of(new Term().setTermTypeId(termType.getId())));
        //更新所有相关的名字
        terms.forEach((term) -> term.setTermTypeName(termType.getName()));
        termDao.saveAll(terms);
        termType.setGmtModified(new Date());
        return termTypeDao.save(termType) != null;
    }

    /**
     * 检查是否有重名
     *
     * @param termType
     * @return
     */
    private boolean checkTermType(TermType termType) {
        return termTypeDao.count(Example.of(new TermType().setName(termType.getName()))) <= 0;
    }

    @Override
    public boolean insertTermType(TermType termType) {
        if (!checkTermType(termType)) {
            return false;
        }
        termType.setGmtCreated(new Date());
        termType.setGmtModified(termType.getGmtCreated());
        return termTypeDao.save(termType) != null;
    }

    @Override
    public boolean updateTermTypeByName(String oldName, String newName) {
        TermType termType = new TermType();
        if (checkTermType(termType.setName(oldName))) {
            //没有这个旧名称
            return false;
        }
        List<TermType> oldTermTypes = termTypeDao.findAll(Example.of(termType.setName(oldName)));
        TermType dbTermType = oldTermTypes.get(0);
        dbTermType.setName(newName);
        //更新
        boolean updated = updateTermType(dbTermType);
        if (!updated) {
            dbTermType.setName(oldName);
        }
        return updated;
    }

    @Override
    public boolean deleteTermTypeById(Long termTypeId) {
        boolean checked = checkTermUseIt(termTypeId);
        if (!checked) {
            return false;
        }
        termTypeDao.deleteById(termTypeId);
        return true;
    }

    private boolean checkTermUseIt(Long termTypeId) {
        return termDao.count(Example.of(new Term().setTermTypeId(termTypeId))) == 0;
    }
}
