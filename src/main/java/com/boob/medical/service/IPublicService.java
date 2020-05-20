package com.boob.medical.service;

import com.boob.medical.entity.Publish;

import java.util.List;

/**
 * publicService接口
 */
public interface IPublicService {


    List<Publish> getPublishListByUserId(Long userId);

    List<Publish> getAllPublish();

    void consent(Long publishId);

    void reject(Long publishId);

    Publish insertPublish(Publish publish);

}
