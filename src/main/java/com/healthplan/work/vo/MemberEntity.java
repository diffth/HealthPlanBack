package com.healthplan.work.vo;


import lombok.Data;

import java.util.Date;


@Data
public class MemberEntity {
    private int mno;
    private String uuid;
    private String phone;
    private String upw;
    private String name;
    private String mtype;
    private String email;
    private String regdate;
    private String sstype;
    private String pcount;

    private String sessionkey;
    private Date sessionlimit;

}
