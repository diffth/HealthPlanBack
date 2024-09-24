package com.healthplan.work.Controller;

import com.healthplan.work.service.SReplyService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SReplyController : 구독 > 댓글
 * The type S reply controller.
 */
@RestController
@Log4j2
@RequestMapping("/sreplies")
public class SReplyController {

    @Autowired
    private SReplyService sreplyService;

}
