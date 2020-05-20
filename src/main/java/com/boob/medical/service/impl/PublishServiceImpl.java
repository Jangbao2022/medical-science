package com.boob.medical.service.impl;

import com.boob.medical.dao.PublishDao;
import com.boob.medical.dao.TermDao;
import com.boob.medical.entity.Publish;
import com.boob.medical.entity.Term;
import com.boob.medical.service.IPublicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class PublishServiceImpl implements IPublicService {

    @Autowired
    private PublishDao publishDao;

    @Autowired
    private TermDao termDao;

    @Override
    public List<Publish> getPublishListByUserId(Long userId) {
        return getPublishList(new Publish().setUserId(userId));
    }

    @Override
    public List<Publish> getAllPublish() {
        return getPublishList(new Publish());
    }

    private List<Publish> getPublishList(Publish publish) {
        List<Publish> publishList = publishDao.findAll(Example.of(publish));
        publishList.stream().sorted(Comparator.comparingInt(Publish::getType));
        return publishList;
    }

    @Override
    public Publish insertPublish(Publish publish) {
        publish.setGmtCreated(new Date());
        publish.setGmtModified(publish.getGmtCreated());
        return publishDao.save(publish);
    }

    @Override
    public void consent(Long publishId) {
        Publish publish = publishDao.getOne(publishId);
        publish.setType(2);//设为同意
        publish.setGmtModified(new Date());
        publishDao.save(publish);
        Long termId = publish.getTermId();
        Term term = termDao.getOne(termId);
        term.setType(1);//设为可查询
        termDao.save(term);

    }

    @Override
    public void reject(Long publishId) {
        Publish publish = publishDao.getOne(publishId);
        publish.setType(3);//设为拒绝
        publish.setGmtModified(new Date());
        publishDao.save(publish);
    }
}
