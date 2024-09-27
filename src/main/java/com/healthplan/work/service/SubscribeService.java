package com.healthplan.work.service;

import com.healthplan.work.dao.SubscribeMapper;
import com.healthplan.work.vo.ImageDTO;
import com.healthplan.work.vo.SearchCriteria;
import com.healthplan.work.vo.SubscribeVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Log4j2
public class SubscribeService {

    @Autowired
    private SubscribeMapper subscribeMapper;

    public List<SubscribeVO> selectSubscribeList() throws Exception {
        return subscribeMapper.selectSubscribeList();
    }

    public SubscribeVO selectSubscrLessionibeRead(int sno) throws Exception {
        subscribeMapper.updateSubscribeLessionCount(sno);
        return subscribeMapper.selectSubscribeLessionRead(sno);
    }

    public void subscribeInsert(SubscribeVO vo) throws Exception {
        subscribeMapper.insertSubscribe(vo);
    }

    public void subscribeUpdate(SubscribeVO vo) throws Exception {
        subscribeMapper.updateSubscribe(vo);
    }

    public void subscribeDelete(int sno) throws Exception {
        subscribeMapper.deleteSubscribe(sno);
    }

    public List<SubscribeVO> selectSubscribeLessionList(SearchCriteria cri) throws Exception {
        return subscribeMapper.selectSubscribeLessionList(cri);
    }

    public int selectSubscribeLessionCount(SearchCriteria cri) throws Exception {
        return subscribeMapper.selectSubscribeLessionCount(cri);
    }

    public void subscribeLessionInsert(SubscribeVO vo) throws Exception {
        subscribeMapper.insertSubscribeLession(vo);

        List<ImageDTO> imageList = vo.getImageDTOList();

        if (imageList != null && !imageList.isEmpty()) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            List<HashMap<String, Object>> listMap = new ArrayList<HashMap<String,Object>>();
            int sno = vo.getSno();

            for (ImageDTO idto : imageList) {
                HashMap<String, Object> data = new HashMap<String, Object>();

                data.put("fileName", "/" + idto.getPath() + "/" +idto.getImgName());
                data.put("sno", sno);
                listMap.add(data);

//                String imgName = idto.getImgName();
//                String uuid = idto.getUuid();
//                String path = idto.getPath();
//                subscribeMapper.subscribeStringAttach(csno, imgName);
            }
            map.put("listMap", listMap);
            subscribeMapper.subscribeAttach(map);
        }

//        String[] files = vo.getFileid();
//
//        if(files != null) {
//            String[] imgType = vo.getImgtype();
//
//            HashMap<String, Object> map = new HashMap<String, Object>();
//            List<HashMap<String, Object>> listMap = new ArrayList<HashMap<String,Object>>();
//
//            int sno = vo.getSno();
//
//            for(int i=0; i < files.length; i++){
//                HashMap<String, Object> data = new HashMap<String, Object>();
//
//                data.put("fileName", files[i]);
//                data.put("sno", sno);
//                data.put("imgtype", imgType[i]);
//                listMap.add(data);
//            }
//            map.put("listMap", listMap);
//            subscribeMapper.subscribeAttach(map);
    }

    public void selectSubscribeUpdate(SubscribeVO vo) throws Exception {
        subscribeMapper.updateSubscribeLession(vo);
    }

    public void subscribeLessionDelete(int sno) throws Exception {
        subscribeMapper.deleteSubscribeLession(sno);
    }

    public SubscribeVO selectSubscribeRead(int sno) throws Exception {
        //subscribeMapper.updateSubscribeCount(sno);
        //return subscribeMapper.selectSubscribeLessionRead(sno);
        return null;
    }

    public List<ImageDTO> selectImageList(int sno) throws Exception {
        return subscribeMapper.selectImageList(sno);
    }
}
