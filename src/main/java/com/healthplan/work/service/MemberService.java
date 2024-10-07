package com.healthplan.work.service;

import com.healthplan.work.dao.MemberMapper;
import com.healthplan.work.vo.MemberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    MemberMapper memberMapper;

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
        //int pmno = mapper.currval(); // 현재 회원번호 조회
        //mapper.setpoint(pmno); // 기본 포인트 등록!
    }

    public int selectIdCount(String uuid) throws Exception {
        return memberMapper.selectIdCount(uuid);
    }
}
