package com.healthplan.work.dao;

import com.healthplan.work.dto.LoginDTO;
import com.healthplan.work.vo.MemberEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * The interface Member mapper.
 */
@Mapper
public interface MemberMapper {

    /**
     * Select member list list.
     *
     * @return the list
     * @throws Exception the exception
     */
    public List<MemberEntity> selectMemberList() throws Exception;

    /**
     * Delete member entity.
     *
     * @param uuid the uuid
     * @return the member entity
     * @throws Exception the exception
     */
    public MemberEntity delete(String uuid) throws Exception;

    /**
     * Insert member.
     *
     * @param member the member
     * @throws Exception the exception
     */
    public void insertMember(MemberEntity member) throws Exception;

    /**
     * Sets point.
     *
     * @param mno the mno
     * @throws Exception the exception
     */
    public void setPoint(int mno) throws Exception;

    /**
     * Select id count int.
     *
     * @param uuid the uuid
     * @return the int
     * @throws Exception the exception
     */
    public int selectIdCount(String uuid) throws Exception;

    /**
     * Select password string.
     *
     * @param uuid the uuid
     * @return the string
     * @throws Exception the exception
     */
    public String selectPassword(String uuid) throws Exception;

    /**
     * Login member entity.
     *
     * @param dto the dto
     * @return the member entity
     * @throws Exception the exception
     */
    public MemberEntity login(LoginDTO dto) throws Exception;

    /**
     * Select member member entity.
     *
     * @param uuid the uuid
     * @return the member entity
     * @throws Exception the exception
     */
    public MemberEntity selectMember(String uuid) throws Exception;

    /**
     * Update member.
     *
     * @param member the member
     * @throws Exception the exception
     */
    public void updateMember(MemberEntity member) throws Exception;

    /**
     * Delete member.
     *
     * @param member the member
     * @throws Exception the exception
     */
    public void deleteMember(MemberEntity member) throws Exception;
}
