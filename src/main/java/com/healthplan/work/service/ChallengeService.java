package com.healthplan.work.service;

import com.healthplan.work.dao.ChallengeMapper;
import com.healthplan.work.vo.ChallengeEntity;
import com.healthplan.work.vo.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Challenge service.
 */
@Service
public class ChallengeService {

    @Autowired
    private ChallengeMapper challengeMapper;

    /**
     * Select challenge list list.
     *
     * @param cri the cri
     * @return the list
     * @throws Exception the exception
     */
    public List<ChallengeEntity> selectChallengeList(SearchCriteria cri) throws Exception {
        return challengeMapper.selectChallengeList(cri);
    }

    /**
     * Select challenge count int.
     *
     * @param cri the cri
     * @return the int
     * @throws Exception the exception
     */
    public int selectChallengeCount(SearchCriteria cri) throws Exception {
        return challengeMapper.selectChallengeCount(cri);
    }

    /**
     * Select challenge read challenge entity.
     *
     * @param bno the bno
     * @return the challenge entity
     * @throws Exception the exception
     */
    public ChallengeEntity selectChallengeRead(int bno) throws Exception {
        challengeMapper.updateChallengeCount(bno);
        return challengeMapper.selectChallengeRead(bno);
    }

    /**
     * Challenge insert.
     *
     * @param vo the vo
     * @throws Exception the exception
     */
    public void challengeInsert(ChallengeEntity vo) throws Exception {
        challengeMapper.insertChallenge(vo);
    }

    /**
     * Challenge update.
     *
     * @param vo the vo
     * @throws Exception the exception
     */
    public void challengeUpdate(ChallengeEntity vo) throws Exception {
        challengeMapper.updateChallenge(vo);
    }

    /**
     * Challenge delete.
     *
     * @param bno the bno
     * @throws Exception the exception
     */
    public void challengeDelete(int bno) throws Exception {
        challengeMapper.deleteChallenge(bno);
    }
}
