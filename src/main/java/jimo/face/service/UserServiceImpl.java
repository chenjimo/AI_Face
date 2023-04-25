package jimo.face.service;

import jimo.face.bean.FaceLog;
import jimo.face.bean.User;
import jimo.face.bean.UserFace;
import jimo.face.dao.FaceDao;
import jimo.face.dao.FaceLogMapper;
import jimo.face.dao.UserFaceMapper;
import jimo.face.dao.UserMapper;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService{
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserFaceMapper userFaceMapper;
    @Resource
    private FaceLogMapper logMapper;
    @Resource
    private FaceDao faceDao;
    /***
     * 根据用户email查找用户信息！
     * @param userEmail
     * @return
     */
    @Override
    public User findUserByEmail(String userEmail) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("email",userEmail);
        User user = userMapper.selectOneByExample(example);
        return user;
    }

    /***
     * 根据用户的ID进行更新对应的imgUrl,同时刷新UserFace的faceToken
     * @param userId
     * @param imgUrl
     * @param faceToken
     * @return
     */
    @Override
    public boolean updateUser(Integer userId, String imgUrl, String faceToken) {
        Example example = new Example(UserFace.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId",userId);
        UserFace userFace = userFaceMapper.selectOneByExample(example);
        int i = userMapper.updateByPrimaryKeySelective(new User(userId, imgUrl));
        if (userFace != null){
            if (!userFace.getFaceToken().equals(faceToken)){
                faceDao.deleteFaceToFaceSet(userFace.getFaceToken());//若曾经还有FaceToken存在需要删除以防数据不安全
            }
            int i1 = userFaceMapper.updateByPrimaryKey(new UserFace(userFace.getId(),userId,faceToken));
            return i>0 && i1>0;
        }else {
            int i1 = userFaceMapper.insert(new UserFace(userId,faceToken));
            return i>0 && i1>0;
        }

    }

    /***
     * 通过faceToken去寻找关联表中对应的user信息
     * @param faceToken
     * @return
     */
    @Override
    public User findUserByToken(String faceToken) {
        Example example = new Example(UserFace.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("faceToken",faceToken);
        UserFace userFace = userFaceMapper.selectOneByExample(example);
        User user = new User();
        user.setId(userFace.getUserId());
        return userMapper.selectOne(user);
    }

    /***
     * 更新face_log的数据信息
     * @param userId
     * @param logTime
     * @param imgUrl
     * @return
     */
    @Override
    public boolean addLog(Integer userId, LocalDateTime logTime, String imgUrl) {
        return logMapper.insert(new FaceLog(userId,logTime,imgUrl))>0;
    }
}
