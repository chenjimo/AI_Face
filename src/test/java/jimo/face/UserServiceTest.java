package jimo.face;

import jimo.face.bean.User;
import jimo.face.dao.UserMapper;
import jimo.face.service.UserService;
import jimo.face.service.UserServiceImpl;
import jimo.face.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
   @Autowired
   private UserServiceImpl userService;
   @Resource
   private UserMapper userMapper;
    /***
     * 根据用户email查找用户信息！
     * @return
     */
    @Test
    public void findUserByEmail() {
        User userByEmail = userService.findUserByEmail("super@aliyun.com");
        System.out.println(userByEmail);
    }

    /***
     * 根据用户的ID进行更新对应的imgUrl,同时刷新UserFace的faceToken
     */
    @Test
    public void updateUser() {
        Integer userId =1;
        String imgUrl  ="https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif";
        String faceToken ="test2";
        boolean b = userService.updateUser(userId, imgUrl, faceToken);
        System.out.println(b);
    }

    /***
     * 通过faceToken去寻找关联表中对应的user信息
     * @return
     */
    @Test
    public void findUserByToken() {
        User test = userService.findUserByToken("test");
        System.out.println(test);
    }

    /***
     * 更新face_log的数据信息
     */
    @Test
    public void addLog() {
        Integer userId =1;
        LocalDateTime logTime = LocalDateTime.now();
        String imgUrl = "test";
        boolean b = userService.addLog(userId, logTime, imgUrl);
        System.out.println(b);
    }
}
