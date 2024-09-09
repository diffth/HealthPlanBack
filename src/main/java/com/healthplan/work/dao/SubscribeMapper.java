package com.healthplan.work.dao;

import com.healthplan.work.vo.SubscribeVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SubscribeMapper {

    public List<SubscribeVO> selectSubscribeList();

    SubscribeVO selectSubscribeRead(int sno);
}
