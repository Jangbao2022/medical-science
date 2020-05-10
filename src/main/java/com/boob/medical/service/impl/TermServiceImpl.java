package com.boob.medical.service.impl;

import com.boob.medical.dao.TermDao;
import com.boob.medical.dto.PageDto;
import com.boob.medical.entity.Term;
import com.boob.medical.service.ITermService;
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

/**
 * termService接口实现类
 */
@Service
public class TermServiceImpl implements ITermService {

    @Autowired
    private TermDao termDao;

    @Override
    public PageDto<Term> getTermPage(int page, int size) {
        return getTermPageDto(page, size, "/term/page", new Term(), null);

    }

    @Override
    public List<Term> getTermList(int size) {
        Page<Term> page = termDao.findAll(PageRequest.of(0, size));
        List<Term> content = page.getContent();
        return content;
    }

    @Override
    public PageDto<Term> getTermPageByType(int page, int size, Long termTypeId) {
        return getTermPageDto(page, size, "/term/page_by_type?type_id=" + termTypeId, new Term().setTermTypeId(termTypeId), null);
    }

    private PageDto<Term> getTermPageDto(int page, int size, String url, Term term, Specification<Term> specification) {
        PageDto<Term> termPageDto = new PageDto<>(url);
        long count;
        if (term != null) {
            count = termDao.count(Example.of(term));
        } else if (specification != null) {
            count = termDao.count(specification);
        } else {
            count = termDao.count(Example.of(new Term()));
        }

        termPageDto.init((int) count, page, size);
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Term> termPage;

        if (term != null) {
            termPage = termDao.findAll(Example.of(term), pageable);
        } else if (specification != null) {
            termPage = termDao.findAll(specification, pageable);
        } else {
            termPage = termDao.findAll(Example.of(new Term()), pageable);
        }
        List<Term> terms = termPage.getContent();
        termPageDto.setElements(terms);
        return termPageDto;
    }

    @Override
    public PageDto<Term> getTermPageByKeyWord(int page, int size, String keyWord) {
        String coreKeyWord = StringUtils.getKeyWordByRemoveSignal(keyWord, "@", "[", "]");

        Specification<Term> specification = SpecificationUtils.getLikeSpecification("name", coreKeyWord);

        PageDto<Term> termPageDto = getTermPageDto(page, size, "/search?key_word=", null, specification);
        return termPageDto;
    }

    @Override
    public Term getTermById(Long termId) {
        Term term = termDao.getOne(termId);
        return term;
    }

    @Override
    public Term updateTerm(Term term) {
        Term dbTerm = termDao.getOne(term.getId());
        term.setGmtCreated(dbTerm.getGmtCreated());
        term.setGmtModified(new Date());
        Term save = termDao.save(term);
        return save;
    }

    @Override
    public Term insertTerm(Term term) {
        term.setGmtCreated(new Date());
        term.setGmtModified(term.getGmtCreated());
        Term save = termDao.save(term);
        return save;
    }

    @Override
    public boolean deleteTermById(Long termId) {
        termDao.deleteById(termId);
        return true;
    }
}
