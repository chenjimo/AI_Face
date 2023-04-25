package jimo.face.service;

import jimo.face.bean.User;

public interface FaceService {
    /***
     * 通过路径获取faceToken
     * @param filePath
     * @return
     */
    String detectFace(String filePath);
    /***
     * 添加face_token到FaceSet
     * @param faceToken
     */
    void addFace(String faceToken);

    /***
     * 登录验证
     * @param filePath
     * @return
     */
    User loginByFace(String filePath);
}
