package jimo.face;

import jimo.face.dao.FaceDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FaceTest {
    @Autowired
    private FaceDao faceDao;
    @Test
    public void deleteFace(){
        faceDao.deleteFaceToFaceSet("ea321519f30c61e5cbfd3c1396e4155d");
    }
    @Test
    public void getFaceSetAll(){
        FaceDao.FaceSetResponseEntity faceSetDetail = faceDao.getFaceSetDetail();
        System.out.println(faceSetDetail);
    }
    @Test
    public void searchFaceToFaceSet(){
        /* E:\Temp\JIMOCode\JavaCode\dev\FaceControll\ */
        FaceDao.FaceEntity faceEntity = faceDao.searchFaceToFaceSet("src\\main\\resources\\static\\img\\user\\jimo.jpg");
        System.out.println(faceEntity);
    }
}
