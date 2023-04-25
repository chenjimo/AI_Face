package jimo.face.util;

import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;

public class ImageUtils {

    /***
     * 将图片保存到本地地址
     * @param imgStr
     * @return
     */
    public static boolean generateImage(String imgStr,String staticPath,String path,String fileName) {
        try {
            if (imgStr == null) {
                return false;
            }
            String  copyPath =staticPath+path+fileName;
            String staticPathName = staticPath + "/img/init/" + fileName;
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] b = decoder.decodeBuffer(imgStr);
            System.out.println(staticPathName);
            File dir = new File(staticPathName);
            if(!dir.exists()&&dir.isDirectory()){//判断文件目录是否存在
                dir.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(dir);
            fos.write(b);
            fos.flush();
            fos.close();
            File dest = new File(copyPath);
            if(!dest.exists()&&dest.isDirectory()){//判断文件目录是否存在
                dest.createNewFile();
            }
            Files.copy(dir.toPath(), dest.toPath());//复制文件信息
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /***
     * 删除文件
     * @param staticPath
     * @param path
     * @param fileName
     * @return
     */
    public static boolean deleteImage(String staticPath,String path,String fileName){
        File file = new File(staticPath + path + fileName);
        return file.delete();
    }

}
