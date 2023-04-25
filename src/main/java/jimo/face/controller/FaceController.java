package jimo.face.controller;


import jimo.face.bean.FaceLog;
import jimo.face.bean.User;
import jimo.face.info.Message;
import jimo.face.service.FaceService;
import jimo.face.service.UserService;
import jimo.face.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@Controller
@RequestMapping("/admin/face")
public class FaceController {
    @Autowired
    private FaceService faceService;
    @Autowired
    private UserService userService;
    private String staticPath = ClassUtils.getDefaultClassLoader().getResource("static").getPath();

//    /**
//     * 调到录入人脸页面
//     * @return
//     */
////    @RequestMapping("/toinput")
////    public String toInput() {
////        return "face/input";
////    }
////
////    @RequestMapping("/tologin")
////    public String toLogin() {
////        return "face/login";
////    }
//
//    /**
//     * 录入人脸
//     *
//     * @param imgData
//     * @throws IOException
//     */
//    @ResponseBody
//    @RequestMapping("/upload")
//    public ResponseEntity doAdd(@RequestParam("imgData") String imgData) throws IOException {
//        String fileName = UUID.randomUUID().toString().replaceAll("-", "") + ".png";
//        ImageUtils.generateImage(imgData.substring(22),staticPath,"/img/user/",fileName);
//        faceService.addFace(staticPath.substring(1) + "/img/init/" + fileName);
//        return ResponseEntity.ok("{\"success\": \"true\"}");
//    }

    /**
     * 人脸识别打卡记录到相应的数据库！
     * @param imgData
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping("/login")
    public ResponseEntity login(@RequestParam("imgData") String imgData) throws IOException {
        String fileName = UUID.randomUUID().toString().replaceAll("-", "") + ".png";
        ImageUtils.generateImage(imgData.substring(22),staticPath,"/img/log/",fileName);
        User user = faceService.loginByFace(staticPath.substring(1) + "/img/init/" + fileName);
        String pathImg = "/static/img/log/" + fileName;//图片映射路径
        if (user==null){
            userService.addLog(-1,LocalDateTime.now(),pathImg);//-1用来表示该人物不存在数据库中
        }else {
            userService.addLog(user.getId(),LocalDateTime.now(),pathImg);
        }
//        user==null||user.getUsername()==null?null:user.getUsername()
       return ResponseEntity.ok("{\"success\": "+user+"}");
    }
}