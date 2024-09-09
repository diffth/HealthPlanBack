package com.healthplan.work.service;

import com.healthplan.work.dao.SubscribeMapper;
import com.healthplan.work.vo.SubscribeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscribeService {

    @Autowired
    private SubscribeMapper subscribeMapper;

    public List<SubscribeVO> selectSubscribeList() {

        return subscribeMapper.selectSubscribeList();
    }

    public SubscribeVO selectSubscribeRead(int sno) {

        return subscribeMapper.selectSubscribeRead(sno);
    }
}
