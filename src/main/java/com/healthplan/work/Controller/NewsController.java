package com.healthplan.work.Controller;

import com.healthplan.work.service.NewsService;
import com.healthplan.work.vo.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@Log4j2
@RequestMapping("/api")
public class NewsController {

	@Autowired
	private NewsService newsService;

	@GetMapping("/news")
	public Map<String, Object> news() throws Exception {
		Map<String, Object> rtnObj = new HashMap<>();

		List<NewsEntity> newsList = newsService.selectList();
		log.info("news-> " + newsList.toString());

		rtnObj.put("newslist", newsList);
		return rtnObj;
	}

	@GetMapping("/hello")
	public String hello() throws Exception {
		return "Hello React!";
	}

	@GetMapping("/list")
	public PageResponseDTO<NewsEntity> list(PageRequestDTO pageRequestDTO) {
		log.info("/api/list-> ");
		PageResponseDTO dto = newsService.selectTodoList(pageRequestDTO);
		log.info("dto -> " + dto.toString());
		return newsService.selectTodoList(pageRequestDTO);
	}

//	@GetMapping("/slist")
//	public PageResponseDTO<SubscribeVO> slist(PageRequestDTO pageRequestDTO) {
//		log.info("/api/slist--------------> " + pageRequestDTO.toString());
//		PageResponseDTO dto = newsService.selectSList(pageRequestDTO);
//		log.info("dto -> " + dto.toString());
//		return newsService.selectSList(pageRequestDTO);
//	}

//	@GetMapping("/slist")
//	public PageResponseDTO<SubscribeVO> slist(PageRequestDTO pageRequestDTO, SearchCriteria cri) {
//		log.info("/api/pageRequestDTO --------------> " + pageRequestDTO);
//		log.info("/api/cri --------------> " + cri);
//
//		//PageMaker pageMaker = new PageMaker();
//		//pageRequestDTO.setCri(cri);
//		//pageRequestDTO.setTotalCount(newsService.selectSubscribeLessionCount());
//		//pageRequestDTO.makeSearch(pageRequestDTO.getPage());
//		//List<SubscribeVO> dtoList = newsService.selectSList(pageRequestDTO);
//		return newsService.selectSList(pageRequestDTO);
//	}

	@GetMapping("/slist")
	public Map<String, Object> slist(SearchCriteria cri) {
		Map<String, Object> result = new HashMap<>();
		log.info("/api/cri --------------> " + cri);

		//PageMaker pageMaker = new PageMaker();
		//pageRequestDTO.setCri(cri);
		//pageRequestDTO.setTotalCount(newsService.selectSubscribeLessionCount());
		//pageRequestDTO.makeSearch(pageRequestDTO.getPage());
		//List<SubscribeVO> dtoList = newsService.selectSList(pageRequestDTO);

		List<SubscribeVO> list = newsService.selectSubList(cri);

		result.put("list", list);
		return result;
	}

	@GetMapping("/{pno}")
	public NewsEntity get(@PathVariable("pno") int pno) {
		log.info("/api/pno-> " + pno);
		NewsEntity vo = newsService.selectRead(pno);
		log.info("/api/vo-> " + vo);
		return vo;
	}
}
