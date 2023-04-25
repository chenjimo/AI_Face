package jimo.face.controller;
 
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import jimo.face.bean.User;
import jimo.face.dao.FaceDao;
import jimo.face.info.Message;
import jimo.face.service.FaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/***
 * 图片的上传并返回对应的静态地址以及包含的全部人脸Tokens
 */
@Controller
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    private FaceDao faceDao;
    /**
     * 上传文件到项目的静态文件目录
     * @param file
     * @return
     */
    @ResponseBody
    @RequestMapping("/uploadImage")
    public Message uploadImage(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID().toString().replaceAll("-", "") + ".png";
    	//获取项目classes/static的地址
        String staticPath = ClassUtils.getDefaultClassLoader().getResource("static").getPath();
        //图片保存路径
        String savePath = staticPath +"/img/user/"+ fileName;
        String staticPathName = staticPath + "/img/init/" + fileName;
        System.out.println("图片保存地址："+savePath);
        /*// 访问路径=静态资源路径+文件目录路径
        String visitPath ="static/" + url_path;
        System.out.println("图片访问uri："+visitPath);*/

        File saveFile = new File(savePath);
        if (!saveFile.exists()){
            saveFile.mkdirs();
        }
        try {
            file.transferTo(saveFile);  //将临时存储的文件移动到真实存储路径下
        } catch (IOException e) {
            e.printStackTrace();
        }
        File dest = new File(staticPathName);
        if(!dest.exists()&&dest.isDirectory()){//判断文件目录是否存在
            dest.createNewFile();
        }
        Files.copy(saveFile.toPath(), dest.toPath());//复制文件信息
        FaceDao.DetectResponseEntity detect = faceDao.detect(staticPath.substring(1) + "/img/init/" + fileName);
        System.out.println(detect);
        return new Message(200,"/static/img/user/" + fileName,detect.getFaces());
    }
    
}