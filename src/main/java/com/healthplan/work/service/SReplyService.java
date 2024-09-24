package com.healthplan.work.service;

import com.healthplan.work.dao.SReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SReplyService {

    @Autowired
    private SReplyMapper sreplyMapper;
}
