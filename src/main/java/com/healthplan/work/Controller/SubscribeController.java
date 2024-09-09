package com.healthplan.work.Controller;

import com.healthplan.work.service.SubscribeService;
import com.healthplan.work.vo.SubscribeVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/subscribe")
public class SubscribeController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SubscribeService subscribeService;

    @RequestMapping("/subscribeList")
    public List<?> subscribeList() throws Exception {
        List<SubscribeVO> list = subscribeService.selectSubscribeList();
        logger.info("subscribeList -> " + list.toString());
        return list;
    }
}
