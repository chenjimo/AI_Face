package jimo.face.service;

import jimo.face.bean.User;

import java.time.LocalDateTime;

public interface UserService {
    /***
     * 根据用户email查找用户信息！
     * @param userEmail
     * @return
     */
    User findUserByEmail(String userEmail);

    /***
     * 根据用户的ID进行更新对应的imgUrl,同时刷新UserFace的faceToken
     * @param userId
     * @param imgUrl
     * @return
     */
    boolean updateUser(Integer userId,String imgUrl,String faceToken);

    /***
     * 通过faceToken去寻找关联表中对应的user信息
     * @param faceToken
     * @return
     */
    User findUserByToken(String faceToken);

    /***
     * 更新face_log的数据信息
     * @param userId
     * @param logTime
     * @param imgUrl
     * @return
     */
    boolean addLog(Integer userId, LocalDateTime logTime,String imgUrl);
}
