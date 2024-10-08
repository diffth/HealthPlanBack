package com.healthplan.work.service;

import com.healthplan.work.dao.MemberMapper;
import com.healthplan.work.dto.LoginDTO;
import com.healthplan.work.util.JwtUtils;
import com.healthplan.work.vo.MemberEntity;

import lombok.extern.log4j.Log4j2;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class MemberService {

    @Autowired
    MemberMapper memberMapper;

    private JwtUtils jwtUtils = new JwtUtils();

    public List<MemberEntity> selectMemberList() throws Exception {
        return memberMapper.selectMemberList();
    }

    public void insertMember(MemberEntity member) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPass = passwordEncoder.encode(member.getUpw());

        member.setUpw(hashedPass);
        memberMapper.insertMember(member);
        int mno = member.getMno();
        memberMapper.setPoint(mno);
    }

    public int selectIdCount(String uuid) throws Exception {
        return memberMapper.selectIdCount(uuid);
    }

    public void login(@NotNull LoginDTO dto) throws Exception {
        String token = jwtUtils.generateToken(dto.getUuid());
        log.info("token -> " + token);

        // JWT 토큰 유효성 검사
        boolean isValidToken = jwtUtils.validateToken(token, dto.getUuid());

        // 저장된 해시된 비밀번호 가져오기
        String password = memberMapper.selectPassword(dto.getUuid());
        log.info("저장된 해시된 비밀번호 가져오기!!!! Stored Hashed Password: " + password);

        // 비밀번호 비교
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean isPasswordMatch = passwordEncoder.matches(dto.getUpw(), password);
        log.info("비밀번호 일치 여부: " + isPasswordMatch);
    }
}
