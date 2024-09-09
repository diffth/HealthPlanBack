package com.healthplan.work.Controller;

import com.healthplan.work.service.SubscribeService;
import com.healthplan.work.vo.SubscribeVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @GetMapping({"/subscribeRead/{sno}", "/subscribeModify/{sno}"})
    public SubscribeVO read(@PathVariable("sno") int sno) throws Exception {
        SubscribeVO vo = subscribeService.selectSubscribeRead(sno);
        logger.info("subscribeRead -> " + vo.toString());
        return vo;
    }
}
