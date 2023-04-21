package jimo.face.service;

public interface FaceService {
    /***
     * 添加face_token
     * @param filePath
     */
    void addFace(String filePath);

    /***
     * 登录验证
     * @param filePath
     * @return
     */
    boolean loginByFace(String filePath);
}
