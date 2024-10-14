package com.healthplan.work.dao;

import com.healthplan.work.dto.LoginDTO;
import com.healthplan.work.vo.MemberEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface MemberMapper {

    // 회원 정보 조회
    public List<MemberEntity> selectMemberList() throws Exception;

    // 회원 번호로 정보 조회
    public MemberEntity selectMno(int mno) throws Exception;

    // 회원 번호로 아이디 조회
    public String selectUuidByMno(Integer mno) throws Exception;

    // 회원 번호로 아이디 조회(리스트에서 일괄)
    public Map<Integer, String> getUuidsByMnos(List<Integer> mnos) throws Exception;

    // 회원가입
    public void insertMem(MemberEntity member) throws Exception;

    // 현재 mno 조회
    public int currval() throws Exception;

    // 포인트 등록
    public void setpoint(Integer mno) throws Exception;

    // 로그인 > 비밀번호 일치 여부 확인
    public String getHashedPasswordByUuid(String uuid) throws Exception;

    // 세션이 있다면 로그인 유지
    public void keepLogin(String uuid, String id, Date sessionLimit) throws Exception;

    // 아이디 중복체크
    public int uuidCk(String uuid) throws Exception;

    // 이메일 중복체크
    public int emailCk(String email) throws Exception;

    // 마이페이지 회원조회
    public MemberEntity selectUuid(String uuid) throws Exception;

    // 마이페이지 회원정보수정
    public void update(MemberEntity member) throws Exception;

    // 회원탈퇴
    public MemberEntity delete(String uuid) throws Exception;

    // 이름 조회
    public MemberEntity selectName(String uuid) throws Exception;

    // 회원번호 조회
    public MemberEntity selectMno(String uuid) throws Exception;

    

    public void insertMember(MemberEntity member) throws Exception;

    public void setPoint(int mno) throws Exception;

    public int selectIdCount(String uuid) throws Exception;

    public String selectPassword(String uuid) throws Exception;

    public MemberEntity login(LoginDTO dto) throws Exception;

    MemberEntity selectMember(String uuid) throws Exception;
}
