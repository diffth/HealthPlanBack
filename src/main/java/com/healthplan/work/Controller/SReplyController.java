package com.healthplan.work.Controller;

import com.healthplan.work.service.SReplyService;
import com.healthplan.work.vo.SReplyVO;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/add")
    public ResponseEntity<String> insert(@RequestBody @NotNull SReplyVO vo) throws Exception {
        ResponseEntity<String> entity = null;

        log.info("add -> " + vo.toString());

        try {
            sreplyService.addReply(vo);
            entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    @GetMapping("/list/{sno}")
    public ResponseEntity<List<SReplyVO>> list(@PathVariable("sno") int sno) throws Exception {
        ResponseEntity<List<SReplyVO>> entity = null;
        log.info("sno -> " + sno);

        try {
            entity = new ResponseEntity<>(sreplyService.listReply(sno), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return entity;
    }
}
