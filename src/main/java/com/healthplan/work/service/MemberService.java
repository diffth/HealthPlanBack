package com.healthplan.work.service;

import com.healthplan.work.dao.MemberMapper;
import com.healthplan.work.dto.LoginDTO;
import com.healthplan.work.util.JwtUtils;
import com.healthplan.work.vo.MemberEntity;

import lombok.extern.log4j.Log4j2;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Member service.
 */
@Service
@Log4j2
public class MemberService {

    /**
     * The Member mapper.
     */
    @Autowired
    MemberMapper memberMapper;

    private JwtUtils jwtUtils = new JwtUtils();

    /**
     * Select member list list.
     *
     * @return the list
     * @throws Exception the exception
     */
    public List<MemberEntity> selectMemberList() throws Exception {
        return memberMapper.selectMemberList();
    }

    /**
     * Insert member.
     *
     * @param member the member
     * @throws Exception the exception
     */
    public void insertMember(MemberEntity member) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPass = passwordEncoder.encode(member.getUpw());

        member.setUpw(hashedPass);
        memberMapper.insertMember(member);
        int mno = member.getMno();
        memberMapper.setPoint(mno);
    }

    /**
     * Select id count int.
     *
     * @param uuid the uuid
     * @return the int
     * @throws Exception the exception
     */
    public int selectIdCount(String uuid) throws Exception {
        return memberMapper.selectIdCount(uuid);
    }

    /**
     * Login confirm response entity.
     *
     * @param dto the dto
     * @return the response entity
     * @throws Exception the exception
     */
    public ResponseEntity loginConfirm(@NotNull LoginDTO dto) throws Exception {
        ResponseEntity result = null;

        String token = jwtUtils.generateToken(dto.getUuid());
        log.info("token -> " + token);

        // JWT 토큰 유효성 검사
        boolean isValidToken = jwtUtils.validateToken(token, dto.getUuid());

        // 저장된 해시된 비밀번호 가져오기
        String password = memberMapper.selectPassword(dto.getUuid());

        // 비밀번호 비교
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean pwMatch = passwordEncoder.matches(dto.getUpw(), password);
        log.info("pwMatch -> " + pwMatch);

        if (pwMatch) {
            // 로그인 성공 시, 토큰과 사용자 정보를 응답 본문과 헤더에 포함하여 클라이언트에 전달
            MemberEntity loggedInMember = memberMapper.login(dto);

            // 응답 헤더에 토큰 포함
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("Authorization", "Bearer " + token);

            // 응답 본문에도 JWT 토큰과 사용자 정보 포함
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("member", loggedInMember);
            responseBody.put("token", token);

            result = new ResponseEntity<>(responseBody, responseHeaders, HttpStatus.OK);

        } else {
            // 비밀번호 불일치 시
            result = new ResponseEntity<>("비밀번호가 일치하지 않습니다.", HttpStatus.UNAUTHORIZED);
        }
        return result;
    }

    /**
     * Login cookie response entity.
     *
     * @param request the request
     * @return the response entity
     * @throws Exception the exception
     */
    public ResponseEntity loginCookie(@NotNull Map<String, String> request) throws Exception {
        ResponseEntity result = null;

        String token = request.get("token");  // token 값 추출

        if (token == null || token.isEmpty()) {
            result = new ResponseEntity<>("토큰이 없습니다.", HttpStatus.UNAUTHORIZED);
        }

        // JWT 토큰에서 uuid 추출 (JwtUtils는 토큰을 처리하는 유틸리티 클래스라고 가정)
        String uuid = jwtUtils.getUuidFromToken(token);

        // 토큰이 유효한지 확인
        if (jwtUtils.validateToken(token, uuid)) {
            //result = ResponseEntity.ok(Map.of("uuid", uuid));  // 유효하면 uuid를 반환
            Map<String, Object> responseVo = new HashMap<>();
            MemberEntity memberInfo = memberMapper.selectMember(uuid);
            responseVo.put("uuid", uuid);
            responseVo.put("vo", memberInfo);
            result = ResponseEntity.ok().body(responseVo);

        } else {
            result =  new ResponseEntity<>("유효하지 않은 토큰입니다.", HttpStatus.UNAUTHORIZED);
        }
        return result;
    }
}
