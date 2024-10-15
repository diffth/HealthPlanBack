package com.healthplan.work.dao;

import com.healthplan.work.vo.ChallengeEntity;
import com.healthplan.work.vo.SearchCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * The interface Challenge mapper.
 */
@Mapper
public interface ChallengeMapper {

    /**
     * Select challenge list list.
     *
     * @param cri the cri
     * @return the list
     * @throws Exception the exception
     */
    List<ChallengeEntity> selectChallengeList(SearchCriteria cri) throws Exception;

    /**
     * Select challenge count int.
     *
     * @param cri the cri
     * @return the int
     * @throws Exception the exception
     */
    public int selectChallengeCount(SearchCriteria cri) throws Exception;

    /**
     * Select challenge read challenge entity.
     *
     * @param bno the bno
     * @return the challenge entity
     * @throws Exception the exception
     */
    public ChallengeEntity selectChallengeRead(int bno) throws Exception;

    /**
     * Insert challenge.
     *
     * @param vo the vo
     * @throws Exception the exception
     */
    void insertChallenge(ChallengeEntity vo) throws Exception;

    /**
     * Update challenge.
     *
     * @param vo the vo
     * @throws Exception the exception
     */
    void updateChallenge(ChallengeEntity vo) throws Exception;

    /**
     * Delete challenge.
     *
     * @param bno the bno
     * @throws Exception the exception
     */
    void deleteChallenge(int bno) throws Exception;

    /**
     * Update challenge count.
     *
     * @param bno the bno
     * @throws Exception the exception
     */
    public void updateChallengeCount(int bno) throws Exception;

}
