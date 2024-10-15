package com.healthplan.work.Controller;

import com.healthplan.work.service.ChallengeService;
import com.healthplan.work.vo.ChallengeEntity;
import com.healthplan.work.vo.PageMaker;
import com.healthplan.work.vo.SearchCriteria;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Challenge controller.
 */
@RestController
@Log4j2
@RequestMapping("/challenge")
public class ChallengeController {

    @Autowired
    private ChallengeService challengeService;

    /**
     * 챌린지 목록 표시
     * Clist map.
     *
     * @param cri the cri
     * @return the map
     * @throws Exception the exception
     */
    @GetMapping("/challengeList")
    public Map<String, Object> clist(SearchCriteria cri) throws Exception {

        log.info("/******************************* 챌린지 리스트");
        Map<String, Object> result = new HashMap<>();

        //전체검색 onchange x
        if ("".equals(cri.getSearchType())) {
            cri.setSearchType("total");
        }
        PageMaker pageMaker = new PageMaker();
        pageMaker.setCri(cri);
        pageMaker.setTotalCount(challengeService.selectChallengeCount(cri));

        List<ChallengeEntity> clist = challengeService.selectChallengeList(cri);
        result.put("clist", clist);
        result.put("pageMaker", pageMaker);

        log.info("cri	-> " + cri);
        log.info("ChallengeList result-> " + result.toString());

        return result;
    }

    /**
     * 챌린지 글 등록
     * Challenge insert string.
     *
     * @param challengeEntity the challenge entity
     * @return the string
     * @throws Exception the exception
     */
    @PostMapping("/challengeinsert")
    public String challengeInsert(ChallengeEntity challengeEntity) throws Exception {

        log.info("ChallengeEntity: mno=" + challengeEntity.getMno() + ", title=" + challengeEntity.getTitle() +
                ", bcontents=" + challengeEntity.getBcontents());
        log.info("Received ChallengeEntity: " + challengeEntity.toString());

        log.info("challengeInsert -> " + challengeEntity);
        challengeService.challengeInsert(challengeEntity);

        return "success";
    }

    /**
     * 챌린지 상세 조회 및 수정 페이지 이동
     * Challenge read challenge entity.
     *
     * @param bno the bno
     * @return the challenge entity
     * @throws Exception the exception
     */
    @GetMapping({"/challengeRead/{bno}", "/challengeModify/{bno}"})
    public ChallengeEntity challengeRead(@PathVariable("bno") int bno) throws Exception {
        // Map<String, Object> result = new HashMap<>();
        ChallengeEntity vo = challengeService.selectChallengeRead(bno);

        log.info("bno -> " + bno);
        log.info("subscribeLessionRead result -> " + vo.toString());

        return vo;
    }

    /**
     * 챌린지 글 수정
     * Challengeupdate string.
     *
     * @param challengeEntity the challenge entity
     * @return the string
     * @throws Exception the exception
     */
    @PutMapping("/challengeupdate")
    public String challengeupdate(ChallengeEntity challengeEntity) throws Exception {
        challengeService.challengeUpdate(challengeEntity);
        log.info("challengeUpdate -> " + challengeEntity.toString());

        return "success";
    }

    /**
     * 챌린지 글 삭제
     * Challengedelete string.
     *
     * @param bno the bno
     * @return the string
     * @throws Exception the exception
     */
    @DeleteMapping("/challengedelete/{bno}")
    public String challengedelete(@PathVariable("bno") int bno) throws Exception {
        log.info("challengeDelete -> " + bno);
        challengeService.challengeDelete(bno);

        return "success";
    }
}
