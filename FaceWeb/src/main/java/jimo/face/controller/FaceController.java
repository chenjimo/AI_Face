package jimo.face.controller;


import jimo.face.service.FaceService;
import jimo.face.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/admin/face")
public class FaceController {
    @Autowired
    private FaceService faceService;

    /**
     * 调到录入人脸页面
     * @return
     */
//    @RequestMapping("/toinput")
//    public String toInput() {
//        return "face/input";
//    }
//
//    @RequestMapping("/tologin")
//    public String toLogin() {
//        return "face/login";
//    }

    /**
     * 录入人脸
     *
     * @param imgData
     * @param request
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping("/upload")
    public ResponseEntity doAdd(@RequestParam("imgData") String imgData, HttpServletRequest request) throws IOException {
        String savePath = request.getServletContext().getRealPath("img/face/");
        String fileName = UUID.randomUUID().toString().replaceAll("-", "") + ".png";
        System.out.println(savePath);
        ImageUtils.generateImage(imgData.substring(22), savePath, fileName);
        faceService.addFace(savePath + fileName);
        return ResponseEntity.ok("{\"success\": \"true\"}");
    }

    /**
     * 人脸识别登录
     * @param imgData
     * @param request
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping("/login")
    public ResponseEntity login(@RequestParam("imgData") String imgData,
                                HttpServletRequest request) throws IOException {
        String savePath =
                request.getServletContext().getRealPath("img/face/login/");
        String fileName = UUID.randomUUID().toString().replaceAll("-", "") + ".png";
        System.out.println(savePath);
        ImageUtils.generateImage(imgData.substring(22), savePath, fileName);
        boolean b = faceService.loginByFace(savePath + fileName);
        if (b) {
            System.out.println("登录成功");
            return ResponseEntity.ok("{\"success\": true}");
        } else {
            System.out.println("登录失败");
            return ResponseEntity.ok("{\"success\": false}");
        }
    }
}