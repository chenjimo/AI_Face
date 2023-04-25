package jimo.face.service;

import jimo.face.bean.User;
import jimo.face.dao.FaceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class FaceServiceImpl implements FaceService {
    @Autowired
    private FaceDao faceDao;
    @Autowired
    private UserService userService;

    /***
     * 通过路径获取faceToken
     * @param filePath
     * @return
     */
    @Override
    public String detectFace(String filePath) {
        FaceDao.DetectResponseEntity detect = faceDao.detect(filePath);
        return detect.getFaces().get(0).getFace_token();
    }

    @Override
    public void addFace(String faceToken) {
        faceDao.addFaceToFaceSet(faceToken);
    }
    @Override
    public User loginByFace(String filePath) {
        FaceDao.FaceEntity faceEntity = faceDao.searchFaceToFaceSet(filePath);
        if (faceEntity==null || faceEntity.getFace_token()==null){
            new File(filePath).delete(); //删除登录人脸
            return null;
        }else {
            User user = userService.findUserByToken(faceEntity.getFace_token());
            if (user==null){
                new File(filePath).delete(); //删除登录人脸
                return null;
            }else {
                new File(filePath).delete(); //删除登录人脸
                return user;
            }
        }

    }
}
