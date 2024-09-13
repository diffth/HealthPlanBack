package com.healthplan.work.Controller;

import com.healthplan.work.service.SubscribeService;
import com.healthplan.work.vo.PageMaker;
import com.healthplan.work.vo.SearchCriteria;
import com.healthplan.work.vo.SubscribeVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * SubscribeController : 구독 > 구독정보 / 전문가구독 / 강의수강
 * The type Subscribe controller.
 */
@RestController
@RequestMapping("/subscribe")
public class SubscribeController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SubscribeService subscribeService;

    /*****************************************************************************************************************
     * Index string.
     *
     * @return the string
     */
    @GetMapping("/")
    public String index() {
        return "redirect:/subscribe/subscribeList";
    }


    /*****************************************************************************************************************
     * subscribeList : 전문가구독 list
     *
     * @return the map
     * @throws Exception the exception
     */
    @GetMapping("/subscribeList")
    public Map<String, Object> list() throws Exception {
        Map<String, Object> result = new HashMap<>();

        List<SubscribeVO> list = subscribeService.selectSubscribeList();
        logger.info("subscribeList -> " + list.toString());

        result.put("slist", list);
        return result;
    }

    /**
     * Insert string.
     *
     * @param subscribeVO the subscribe vo
     * @return the string
     * @throws Exception the exception
     */
    @PostMapping("/subscribeInsert")
    public String insert(SubscribeVO subscribeVO) throws Exception {
        subscribeService.subscribeInsert(subscribeVO);
        logger.info("subscribeInsert -> " + subscribeVO.toString());

        return "redirect:/subscribe/subscribeList";
    }

    /**
     * Read map.
     *
     * @param sno the sno
     * @return the map
     * @throws Exception the exception
     */
    @GetMapping({"/subscribeRead/{sno}", "/subscribeModify/{sno}"})
    public Map<String, Object> read(@PathVariable("sno") int sno) throws Exception {
        Map<String, Object> result = new HashMap<>();
        SubscribeVO vo = subscribeService.selectSubscribeRead(sno);
        logger.info("subscribeRead -> " + vo.toString());

        result.put("vo", vo);
        return result;
    }

    /**
     * Update string.
     *
     * @param subscribeVO the subscribe vo
     * @return the string
     * @throws Exception the exception
     */
    @PutMapping("/subscribeUpdate")
    public String update(SubscribeVO subscribeVO) throws Exception {
        subscribeService.subscribeUpdate(subscribeVO);
        logger.info("subscribeUpdate -> " + subscribeVO.toString());

        return "redirect:/subscribe/subscribeList";
    }

    /**
     * Delete string.
     *
     * @param sno the sno
     * @return the string
     * @throws Exception the exception
     */
    @DeleteMapping("/subscribeDelete/{sno}")
    public String delete(@PathVariable("sno") int sno) throws Exception {
        subscribeService.subscribeDelete(sno);
        logger.info("subscribeDelete -> " + sno);

        return "redirect:/subscribe/subscribeList";
    }
    
    /*****************************************************************************************************************
     * subscribeLessionList : 강의수강 list
     *
     * @return the map
     * @throws Exception the exception
     */
    @GetMapping("/subscribeLessionList")
    public Map<String, Object> lessionList(@RequestParam(value = "num", required = false) String num, SearchCriteria cri) throws Exception {
        Map<String, Object> result = new HashMap<>();
        List<SubscribeVO> list = subscribeService.selectSubscribeLessionList(cri);

        PageMaker pageMaker = new PageMaker();
        pageMaker.setCri(cri);
        pageMaker.setTotalCount(subscribeService.selectSubscribeLessionCount(cri));
        pageMaker.makeSearch(3);
        logger.info("subscribeLessionList -> " + list.toString());
        logger.info("cri -> " + cri.toString());
        logger.info("page -> " + num);

        result.put("llist", list);
        result.put("pageMaker", pageMaker);
        return result;
    }
}
