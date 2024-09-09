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

    public List<SubscribeVO> selectSubscribeList() throws Exception {

        return subscribeMapper.selectSubscribeList();
    }

    public SubscribeVO selectSubscribeRead(int sno) throws Exception {

        return subscribeMapper.selectSubscribeRead(sno);
    }

    public void subscribeInsert(SubscribeVO vo) throws Exception {

        subscribeMapper.insertSubscribe(vo);
    }

    public void subscribeUpdate(SubscribeVO vo) throws Exception {

        subscribeMapper.updateSubscribe(vo);
    }

    public void subscribeDelete(int sno) throws Exception {

        subscribeMapper.deleteSubscribe(sno);
    }
}
