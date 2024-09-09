package com.healthplan.work.Controller;

import com.healthplan.work.service.SubscribeService;
import com.healthplan.work.vo.SubscribeVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscribe")
public class SubscribeController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SubscribeService subscribeService;

    @GetMapping("/")
    public String index() {
        return "redirect:/subscribe/subscribeList";
    }

    @GetMapping("/subscribeList")
    public List<?> list() throws Exception {
        List<SubscribeVO> list = subscribeService.selectSubscribeList();
        logger.info("subscribeList -> " + list.toString());
        return list;
    }

    @PostMapping("/subscribeInsert")
    public String insert(SubscribeVO subscribeVO) throws Exception {
        subscribeService.subscribeInsert(subscribeVO);
        logger.info("subscribeInsert -> " + subscribeVO.toString());

        return "redirect:/subscribe/subscribeList";
    }

    @GetMapping({"/subscribeRead/{sno}", "/subscribeModify/{sno}"})
    public SubscribeVO read(@PathVariable("sno") int sno) throws Exception {
        SubscribeVO vo = subscribeService.selectSubscribeRead(sno);
        logger.info("subscribeRead -> " + vo.toString());
        return vo;
    }

    @PutMapping("/subscribeUpdate")
    public String update(SubscribeVO subscribeVO) throws Exception {
        subscribeService.subscribeUpdate(subscribeVO);
        logger.info("subscribeUpdate -> " + subscribeVO.toString());

        return "redirect:/subscribe/subscribeList";
    }

    @DeleteMapping("/subscribeDelete/{sno}")
    public String delete(@PathVariable("sno") int sno) throws Exception {
        subscribeService.subscribeDelete(sno);
        logger.info("subscribeDelete -> " + sno);

        return "redirect:/subscribe/subscribeList";
    }
}
