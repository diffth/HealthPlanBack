package com.healthplan.work.Controller;

import com.healthplan.work.service.NewsService;
import com.healthplan.work.vo.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Log4j2
@RequestMapping("/api")
public class NewsController {

	@Autowired
	private NewsService newsService;

	@GetMapping("/news")
	public @ResponseBody Map<String, Object> news() throws Exception {
		Map<String, Object> result = new HashMap<>();

		List<NewsEntity> newslist = newsService.selectList();
		result.put("newslist", newslist);
		log.info("result-> " + result.toString());

		return result;
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


	@GetMapping("/slist")
	public Map<String, Object> slist(SearchCriteria cri) {
		Map<String, Object> result = new HashMap<>();

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(newsService.selectSubscribeLessionCount(cri));

		List<SubscribeVO> slist = newsService.selectSubList(cri);
		result.put("slist", slist);
		result.put("pageMaker", pageMaker);
		log.info("cri	-> " + cri);
		log.info("result-> " + result.toString());
		log.info("pageMaker-> getTotalCount() : " + pageMaker.getTotalCount());
		log.info("pageMaker-> getStartPage()  : " + pageMaker.getStartPage());
		log.info("pageMaker-> getEndPage()    : " + pageMaker.getEndPage());
		log.info("pageMaker-> isNext()        : " + pageMaker.isNext());
		log.info("pageMaker-> isPrev()        : " + pageMaker.isPrev());
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
