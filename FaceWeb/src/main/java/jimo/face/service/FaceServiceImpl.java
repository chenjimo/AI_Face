package jimo.face.service;

import jimo.face.dao.FaceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.File;

@Service
public class FaceServiceImpl implements FaceService {
    @Autowired
    private FaceDao faceDao;
    @Override
    public void addFace(String filePath) {
        FaceDao.FaceSetResponseEntity fs = null;
        try {
            fs = faceDao.getFaceSetDetail();
        } catch (Exception e) {
        }
        if (fs == null) { //faceset不存在
            faceDao.faceSetCreate();
        }
        FaceDao.DetectResponseEntity dr = faceDao.detect(filePath); //检视人脸
        for (FaceDao.FaceEntity f : dr.getFaces()) {
            faceDao.addFaceToFaceSet(f.getFace_token());
        }
    }
    @Override
    public boolean loginByFace(String filePath) {
        boolean result = false;
        FaceDao.FaceSetResponseEntity fs = null;
        try {
            fs = faceDao.getFaceSetDetail();
        } catch (Exception e) {
        }
        if (fs == null) { //faceset不存在
            faceDao.faceSetCreate();
            fs = faceDao.getFaceSetDetail();
        }
        FaceDao.DetectResponseEntity dr = faceDao.detect(filePath); //检视人脸
        String ft1 = null;
        if (dr.getFace_num() >=1) {
            ft1 = dr.getFaces().get(0).getFace_token();
        } else {
            return false;
        }
        for (String ft2: fs.getFace_tokens()) {
            //这里用的是循环比对结果进行核算是否存在，在性能上不是最优解，建议用库查询的方式进行核验存在！
            if (faceDao.compareFace(ft1, ft2)) {
                result = true;
            }
        }
        new File(filePath).delete(); //删除登录人脸
        return result;
    }
}
