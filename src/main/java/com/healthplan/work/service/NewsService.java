package com.healthplan.work.service;

import com.healthplan.work.dao.NewsMapper;
import com.healthplan.work.vo.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class NewsService {

    @Autowired
    private NewsMapper newsMapper;

    public List<NewsEntity> selectList() {
        return newsMapper.listNews();
    }

    public PageResponseDTO<NewsEntity> selectTodoList(PageRequestDTO pageRequestDTO) {

//        Pageable pageable = PageRequest.of(pageRequestDTO.getPage() - 1,
//                pageRequestDTO.getSize(),
//                Sort.by("pno").descending());

//        Page<Todo> result = todoRepository.findAll(pageable);

//        List<NewsEntity> dtoList = result.getContent().stream()
//                .map(todo -> modelMapper.map(todo, NewsEntity.class))
//                .collect(Collectors.toList());

        //long totalCount = result.getTotalElements();

        List<NewsEntity> dtoList = newsMapper.listNews();
        PageResponseDTO<NewsEntity> responseDTO = PageResponseDTO.<NewsEntity>withAll()
                .dtoList(dtoList)
                .pageRequestDTO(pageRequestDTO)
                .totalCount(2)
                .build();

        return responseDTO;
    }

    public NewsEntity selectRead(int pno) {

        return newsMapper.selectRead(pno);
    }


//    public PageResponseDTO<SubscribeVO> selectSList(PageRequestDTO pageRequestDTO) {
//
//        List<SubscribeVO> dtoList = newsMapper.listSub(pageRequestDTO);
//        log.info("==================> " + dtoList.toString());
//        PageResponseDTO<SubscribeVO> responseDTO = PageResponseDTO.<SubscribeVO>withAll()
//                .dtoList(dtoList)
//                .pageRequestDTO(pageRequestDTO)
//                .totalCount(newsMapper.selectCount())
//                .build();
//
//        return responseDTO;
//    }

    public int selectSubscribeLessionCount(Criteria cri) {
        return newsMapper.selectCount(cri);
    }

    public List<SubscribeVO> selectSubList(Criteria cri) {

        return newsMapper.subList(cri);
    }
}
