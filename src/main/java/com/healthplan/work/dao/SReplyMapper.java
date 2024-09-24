package com.healthplan.work.dao;

import com.healthplan.work.vo.SReplyVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SReplyMapper {

    public void addReply(SReplyVO vo) throws Exception;

    public List<SReplyVO> listReply(int sno) throws Exception;


}
