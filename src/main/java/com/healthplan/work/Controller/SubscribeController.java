package com.healthplan.work.Controller;

import com.healthplan.work.service.SubscribeService;
import com.healthplan.work.vo.PageMaker;
import com.healthplan.work.vo.SearchCriteria;
import com.healthplan.work.vo.SubscribeVO;
import lombok.extern.log4j.Log4j2;
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
@Log4j2
@RequestMapping("/subscribe")
public class SubscribeController {

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
        log.info("subscribeList -> " + list.toString());

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
        log.info("subscribeInsert -> " + subscribeVO.toString());

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
        log.info("subscribeRead -> " + vo.toString());

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
        log.info("subscribeUpdate -> " + subscribeVO.toString());

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
        log.info("subscribeDelete -> " + sno);

        return "redirect:/subscribe/subscribeList";
    }

    /*****************************************************************************************************************
     * subscribeLessionList : 강의수강 list
     *
     * @param cri the cri
     * @return the map
     * @throws Exception the exception
     */
    @GetMapping("/subscribeLessionList")
    public Map<String, Object> lessionList(SearchCriteria cri) throws Exception {
        Map<String, Object> result = new HashMap<>();

        PageMaker pageMaker = new PageMaker();
        pageMaker.setCri(cri);
        pageMaker.setTotalCount(subscribeService.selectSubscribeLessionCount(cri));

        List<SubscribeVO> list = subscribeService.selectSubscribeLessionList(cri);
        result.put("list", list);
        result.put("pageMaker", pageMaker);

        log.info("cri	-> " + cri);
        log.info("subscribeLessionList result-> " + result.toString());
        return result;
    }

    /**
     * subscribeLessionInsert : 강의등록
     * Lession insert string.
     *
     * @param subscribeVO the subscribe vo
     * @return the string
     * @throws Exception the exception
     */
    @PostMapping("/subscribeLessionInsert")
    public String lessionInsert(SubscribeVO subscribeVO) throws Exception {
        log.info("subscribeInsert -> " + subscribeVO);
        subscribeService.subscribeLessionInsert(subscribeVO);

        return "success";
    }

    /**
     * subscribeLessionRead : 강의상세
     * Lession read subscribe vo.
     *
     * @param sno the sno
     * @return the subscribe vo
     * @throws Exception the exception
     */
    @GetMapping("/subscribeLessionRead/{sno}")
    public SubscribeVO lessionRead(@PathVariable("sno") int sno) throws Exception {
        SubscribeVO vo = subscribeService.selectSubscribeRead(sno);

        log.info("sno -> " + sno);
        log.info("subscribeLessionRead result -> " + vo.toString());

        //이미지 정보 가져오기
        //List<SubscribeVO> imageDTOList = getImageDTOList(bNo);
        return vo;
    }

    /**
     * subscribeLessionRead : 강의수정
     * Lession update subscribe vo.
     *
     * @param subscribeVO the subscribe vo
     * @return the subscribe vo
     * @throws Exception the exception
     */
    @PostMapping("/subscribeLessionUpdate/{sno}")
    public String lessionUpdate(SubscribeVO subscribeVO) throws Exception {
        subscribeService.selectSubscribeUpdate(subscribeVO);

        log.info("subscribeVO -> " + subscribeVO);
//        log.info("subscribeLessionRead result -> " + vo.toString());

        //이미지 정보 가져오기
        //List<SubscribeVO> imageDTOList = getImageDTOList(bNo);

        return "success";
    }

    /**
     * subscribeLessionDelete : 강의삭제
     * Lession delete string.
     *
     * @param sno the sno
     * @return the string
     * @throws Exception the exception
     */
    @DeleteMapping("/subscribeLessionDelete/{sno}")
    public String lessionDelete(@PathVariable("sno") int sno) throws Exception {
        subscribeService.subscribeDelete(sno);
        log.info("subscribeDelete -> " + sno);

        return "redirect:/subscribe/subscribeLessionList";
    }
}
