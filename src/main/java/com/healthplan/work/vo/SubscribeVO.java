package com.healthplan.work.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SubscribeVO {

    private int sno;
    private int mno;
    private String uuid;
    private String stype;
    private String title;
    private String contents;
    private String wdate;
    private int counts;
    private String[] fileid;
    private String[] imgtype;
    private String titleimg;
    private String replycnt;
    private int spoint;

    private List<ImageDTO> imageDTOList = new ArrayList<>();

}
