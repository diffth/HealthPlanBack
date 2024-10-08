package com.healthplan.work.Controller;

import com.healthplan.work.dao.MemberMapper;
import com.healthplan.work.dto.LoginDTO;
import com.healthplan.work.service.MemberService;
import com.healthplan.work.util.JwtUtils;
import com.healthplan.work.vo.MemberEntity;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import java.util.Date;
import java.util.HashMap;
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

//    private JwtUtils jwtUtils = new JwtUtils();

    // private PasswordEncoder passwordEncoder;
//    private final PasswordEncoder passwordEncoder;


    /**
     * Instantiates a new Member controller.
     *
     * @param passwordEncoder the password encoder
     */
//    public MemberController(PasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//    }

    @GetMapping("/list")
    public List<MemberEntity> mList() throws Exception {

        List<MemberEntity> list = memberService.selectMemberList();
        log.info("/member/list -> " + list);

        return list;
    }

    @PostMapping("/uuidCk")
    public int uuidChk(@RequestBody Map<String, String> request) throws Exception {

        String uuid = request.get("uuid");
        log.info("/member/uuidCk -> " + uuid);

        int result = memberService.selectIdCount(uuid);
        return result;
    }

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


    /*
    @PostMapping("/loginPost")
    public  ResponseEntity<?> loginPOST(@RequestBody @NotNull LoginDTO dto) throws Exception {
        
    try {
        final String token = jwtUtils.generateToken(dto.getUuid());
        log.info("/*** 인코딩 !!!! token=" + token);
        
        // JWT 토큰 유효성 검사
        boolean isValidToken = jwtUtils.validateToken(token, dto.getUuid());
        log.info("토큰 유효성: " + isValidToken);
        
        // 저장된 해시된 비밀번호 가져오기
        String storedHashedPassword = mapper.getHashedPasswordByUuid(dto.getUuid());
        log.info("저장된 해시된 비밀번호 가져오기!!!! Stored Hashed Password: " + storedHashedPassword);
        
        // 비밀번호 비교
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean isPasswordMatch = passwordEncoder.matches(dto.getUpw(), storedHashedPassword);
        log.info("비밀번호 일치 여부: " + isPasswordMatch);
        
        if (isPasswordMatch) {
            // 로그인 성공 시, 토큰과 사용자 정보를 응답 본문과 헤더에 포함하여 클라이언트에 전달
            MemberEntity loggedInMember = mapper.login(dto);
            
            // 응답 헤더에 토큰 포함
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("Authorization", "Bearer " + token);
            
            // 응답 본문에도 JWT 토큰과 사용자 정보 포함
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("member", loggedInMember);
            responseBody.put("token", token);
            
            return new ResponseEntity<>(responseBody, responseHeaders, HttpStatus.OK);
            
        } else {
            // 비밀번호 불일치 시
            return new ResponseEntity<>("비밀번호가 일치하지 않습니다.", HttpStatus.UNAUTHORIZED);
        }
    } catch (Exception e) {
        log.error("로그인 처리 중 오류 발생", e);
        return new ResponseEntity<>("서버 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
*/

    /**
     * Login cookie response entity.
     *
     * @param requestData the request data
     * @return the response entity
     * @throws Exception the exception
     */
// 쿠키 관리
    /*@RequestMapping(value = "/loginCookie", method = RequestMethod.POST)
    public  ResponseEntity<?> loginCookie(@RequestBody @NotNull Map<String, String> requestData) throws Exception {

        log.info("/*************** /loginCookie 시작...");
        //logger.info("/*************** dto 뭐 받았니"+dto.toString());

        String token = requestData.get("token");  // token 값 추출
        System.out.println("/***************  받은 토큰 보자!!! =" + token);

        if (token == null || token.isEmpty()) {
            return new ResponseEntity<>("토큰이 없습니다.", HttpStatus.UNAUTHORIZED);
        }

        try {
            // JWT 토큰에서 uuid 추출 (JwtUtils는 토큰을 처리하는 유틸리티 클래스라고 가정)
            String uuid = jwtUtils.getUuidFromToken(token);

            // 토큰이 유효한지 확인
            if (jwtUtils.validateToken(token, uuid)) {
                log.info("/*************** token의 id 보여줘  " + uuid);

                return ResponseEntity.ok(Map.of("uuid", uuid));  // 유효하면 uuid를 반환

            } else {
                return new ResponseEntity<>("유효하지 않은 토큰입니다.", HttpStatus.UNAUTHORIZED);
            }

        } catch (Exception e) {
            return new ResponseEntity<>("서버 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }*/

    /**
     * Logout string.
     *
     * @param request  the request
     * @param response the response
     * @param session  the session
     * @return the string
     * @throws Exception the exception
     */
// 로그아웃
    /*@RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session)
            throws Exception {

        Object obj = session.getAttribute("login");

        if (obj != null) {
            MemberEntity mem = (MemberEntity) obj;

            session.removeAttribute("login");
            session.invalidate();

            Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");

            if (loginCookie != null) {
                loginCookie.setPath("/");
                loginCookie.setMaxAge(0);
                response.addCookie(loginCookie);
                mapper.keepLogin(mem.getUuid(), session.getId(), new Date());
            }
        }
        return "success";
    }*/

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
     * Select mem id response entity.
     *
     * @param requestData the request data
     * @return the response entity
     * @throws Exception the exception
     */
// 마이페이지 회원정보 조회
    /*@RequestMapping(value = "/read", method = RequestMethod.POST)
    public ResponseEntity<MemberEntity> selectMemId(@RequestBody Map<String, String> requestData) throws Exception {

        // Map에서 "uuid" 값 추출
        String uuid = requestData.get("uuid");
        // 로그로 uuid 확인
        log.info("조회할 아이디 : " + uuid);

        MemberEntity memberInfo = mapper.selectUuid(uuid);

        log.info("/************************** 일단 마이페이지 정보 보여줘" + memberInfo);

        if (memberInfo != null) {
            log.info("/************************** 성공한 마이페이지 정보 보여줘" + memberInfo);
            return ResponseEntity.ok(memberInfo);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // return mapper.selectUuid(mem.getUuid());
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
