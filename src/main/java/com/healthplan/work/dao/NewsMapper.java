package com.healthplan.work.dao;

import com.healthplan.work.vo.Criteria;
import com.healthplan.work.vo.NewsEntity;
import com.healthplan.work.vo.PageRequestDTO;
import com.healthplan.work.vo.SubscribeVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NewsMapper {

	public List<NewsEntity> listNews();

	public NewsEntity selectRead(int pno);

	public List<SubscribeVO> listSub(PageRequestDTO pageDto);

	int selectCount();

	List<SubscribeVO> subList(Criteria cri);
}
