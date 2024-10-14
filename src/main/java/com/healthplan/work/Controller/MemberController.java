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
     * Gets uuid by mno.
     *
     * @param mem the mem
     * @return the uuid by mno
     * @throws Exception the exception
     */
    /*@PostMapping("/getUuidByMno")
    public ResponseEntity<Map<String, String>> getUuidByMno(@RequestBody MemberEntity mem) throws Exception {
        log.info("회원정보 조회 요청 - mno: " + mem.getMno());

        // mno로 uuid 조회
        String uuid = mapper.selectUuidByMno(mem.getMno());
        log.info("/******************************* uuid 조회 당한 회원번호 보여줘 : " + uuid);

        if (uuid != null) {
            // 응답 데이터를 JSON 형식으로 반환
            Map<String, String> result = new HashMap<>();
            result.put("uuid", uuid);

            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            // 해당 mno에 대한 uuid가 없을 경우
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/


    /**
     * Gets uuids by mnos.
     *
     * @param mnos the mnos
     * @return the uuids by mnos
     * @throws Exception the exception
     */
    /*@PostMapping("/getUuidsByMnos")
    public Map<Integer, String> getUuidsByMnos(@RequestBody List<Integer> mnos) throws Exception {
        return mapper.getUuidsByMnos(mnos);
    }*/


    /**
     * Select mno response entity.
     *
     * @param requestData the request data
     * @return the response entity
     * @throws Exception the exception
     */
// 회원번호 조회
    /*@RequestMapping(value = "/readMno", method = RequestMethod.POST)
    public ResponseEntity<MemberEntity> selectMno(@RequestBody Map<String, String> requestData) throws Exception {

        // Map에서 "uuid" 값 추출
        String uuid = requestData.get("uuid");
        // 로그로 uuid 확인
        log.info("조회할 아이디 : " + uuid);

        MemberEntity memberInfo = mapper.selectMno(uuid);

        log.info("/************************** 일단 회원번호 정보 보여줘" + memberInfo);

        if (memberInfo != null) {
            log.info("/************************** 성공한 회원번호 정보 보여줘" + memberInfo);
            return ResponseEntity.ok(memberInfo);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/

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
     * Email ck int.
     *
     * @param email the email
     * @return the int
     * @throws Exception the exception
     */
// 이메일 중복체크
    /*@RequestMapping(value = "/emailCk", method = RequestMethod.POST)
    public int emailCk(@RequestBody String email) throws Exception {
        log.info("/******************** 포스트 돌겠습니다 !! emailCk post ...........");
        log.info(email.toString());

        int result = mapper.emailCk(email);

        return result;
    }*/

    /**
     * Select name response entity.
     *
     * @param requestData the request data
     * @return the response entity
     * @throws Exception the exception
     */
// 이름 조회
    /*@RequestMapping(value = "/readName", method = RequestMethod.POST)
    public ResponseEntity<MemberEntity> selectName(@RequestBody Map<String, String> requestData) throws Exception {

        // Map에서 "uuid" 값 추출
        String uuid = requestData.get("uuid");
        // 로그로 uuid 확인
        log.info("조회할 아이디 : " + uuid);

        MemberEntity memberInfo = mapper.selectName(uuid);

        log.info("/************************** 일단 이름 정보 보여줘" + memberInfo);

        if (memberInfo != null) {
            log.info("/************************** 성공한 이름 정보 보여줘" + memberInfo);
            return ResponseEntity.ok(memberInfo);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/

    /**
     * Update post string.
     *
     * @param mem  the mem
     * @param rttr the rttr
     * @return the string
     * @throws Exception the exception
     */
// 마이페이지 회원정보 수정
    /*@RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String updatePOST(@RequestBody MemberEntity mem, RedirectAttributes rttr) throws Exception {

        log.info(mem.toString());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(mem.getUpw());

        System.out.println("해시된 비밀번호:" + hashedPassword);

        mem.setUpw(hashedPassword);

        mapper.update(mem);

        rttr.addAttribute("name", mem.getName());
        rttr.addFlashAttribute("msg", "SUCCESS");

        log.info(rttr.toString());

        return "SUCCESS";

    }*/

    /**
     * Delete string.
     *
     * @param mem the mem
     * @return the string
     * @throws Exception the exception
     */
// 회원탈퇴
    /*@RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String delete(@RequestBody MemberEntity mem) throws Exception {

        log.info("delete post ...........");
        log.info(mem.toString());

        mapper.delete(mem.getUuid());

        return "succ";
    }*/
}
