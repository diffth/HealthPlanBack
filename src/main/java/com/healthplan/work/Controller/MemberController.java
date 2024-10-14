package com.healthplan.work.Controller;

import com.healthplan.work.dto.LoginDTO;
import com.healthplan.work.service.MemberService;
import com.healthplan.work.vo.MemberEntity;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * The type Member controller.
 */
@RestController
@Log4j2
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    /**
     * Select list.
     *
     * @return the list
     * @throws Exception the exception
     */
    @GetMapping("/list")
    public List<MemberEntity> mList() throws Exception {

        List<MemberEntity> list = memberService.selectMemberList();
        log.info("/member/list -> " + list);

        return list;
    }

    /**
     * Uuid chk int.
     *
     * @param request the request
     * @return the int
     * @throws Exception the exception
     */
    @PostMapping("/uuidCk")
    public int uuidChk(@RequestBody Map<String, String> request) throws Exception {

        String uuid = request.get("uuid");
        log.info("/member/uuidCk -> " + uuid);

        int result = memberService.selectIdCount(uuid);
        return result;
    }

    /**
     * M insert string.
     *
     * @param member the member
     * @return the string
     * @throws Exception the exception
     */
    @PostMapping("/register")
    public String mInsert(@RequestBody MemberEntity member) throws Exception {
        log.info("/member/register -> " + member);

        memberService.insertMember(member);
        return "success";
    }

    /**
     * Login post response entity.
     *
     * @param dto the dto
     * @return the response entity
     * @throws Exception the exception
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @NotNull LoginDTO dto) throws Exception {
        log.info("/member/login -> " + dto);

        ResponseEntity responseEntity = null;

        try {
           responseEntity = memberService.loginConfirm(dto);

        } catch (Exception e) {
            log.error("로그인 처리 중 오류 발생", e);
            responseEntity = new ResponseEntity<>("서버 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    /**
     * Login cookie response entity.
     *
     * @param request the request
     * @return the response entity
     * @throws Exception the exception
     */
    @PostMapping("/loginCookie")
    public ResponseEntity<?> loginCookie(@RequestBody @NotNull Map<String, String> request) throws Exception {
        log.info("/member/loginCookie -> " + request);

        ResponseEntity responseEntity = null;

        try {
            responseEntity = memberService.loginCookie(request);

        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("서버 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    /**
     * Update post string.
     *
     * @param member the member
     * @return the string
     * @throws Exception the exception
     */
    @PostMapping("/modify")
    public String loginModify(@RequestBody @NotNull MemberEntity member) throws Exception {
        log.info("/member/modify -> " + member);

        memberService.updateMember(member);
        return "success";
    }

    /**
     * Delete string.
     *
     * @param member the member
     * @return the string
     * @throws Exception the exception
     */
    @PostMapping("/remove")
    public String memberDelete(@RequestBody MemberEntity member) throws Exception {
        log.info("/member/remove -> " + member);

        memberService.deleteMember(member);
        return "success";
    }
}
