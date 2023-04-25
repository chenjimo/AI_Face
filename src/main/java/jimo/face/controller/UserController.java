package jimo.face.controller;

import jimo.face.bean.User;
import jimo.face.dao.FaceDao;
import jimo.face.info.Message;
import jimo.face.service.FaceService;
import jimo.face.service.UserService;
import jimo.face.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

/***
 * 1、用户的信息查找
 * 2、打卡记录的查找展示
 */
@Controller
@ResponseBody
@RequestMapping("/admin/")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private FaceDao faceDao;
    @Autowired
    private FaceService faceService;
    private String staticPath = ClassUtils.getDefaultClassLoader().getResource("static").getPath();
    /***
     * 通过email获取User信息
     * @return
     */
    @GetMapping("user")
    public Message findUser(@RequestParam("email") String email, HttpServletRequest request){
        User userByEmail = userService.findUserByEmail(email);
        request.getSession().setAttribute("UserID",userByEmail.getId());
        return new Message(userByEmail==null?404:200,userByEmail==null?"查询失败！！！":"查询成功！",userByEmail);
    }

    /***
     * 通过Id更新User&UserFace信息
     * @return
     */
    @PostMapping("uploadImage")
    public Message updateUser(@RequestParam("img") MultipartFile img,@RequestParam("id") Integer id) throws IOException {
//         int id= (int)request.getSession().getAttribute("UserID");
        String fileName = UUID.randomUUID().toString().replaceAll("-", "") + ".png";
        String savePath = staticPath +"/img/user/"+ fileName;
        String staticPathName = staticPath + "/img/init/" + fileName;
        File saveFile = new File(savePath);
        if (!saveFile.exists()){
            saveFile.mkdirs();
        }
        try {
            img.transferTo(saveFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        File dest = new File(staticPathName);
        if(!dest.exists()&&dest.isDirectory()){//判断文件目录是否存在
            dest.createNewFile();
        }
        Files.copy(saveFile.toPath(), dest.toPath());//复制文件信息
        String facePath = staticPath.substring(1) + "/img/init/" + fileName;
        FaceDao.FaceEntity faceEntity = faceDao.searchFaceToFaceSet(facePath);//先判断库中有没有该脸信息
        String faceToken = null;
        if (faceEntity==null){
             faceToken = faceService.detectFace(facePath);
             faceService.addFace(faceToken);
        }else {
            faceToken = faceEntity.getFace_token();
        }
        boolean b = userService.updateUser(id, "/static/img/user/" + fileName, faceToken);
        User user = userService.findUserByToken(faceToken);//更新后的用户信息
        return new Message(b?200:500,b?"人脸更新成功！":"人脸更新失败！！！",user);
    }

}
