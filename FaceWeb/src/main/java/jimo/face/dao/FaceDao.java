package jimo.face.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.file.Paths;
import java.util.List;

@ConfigurationProperties("face.config")
@Component
@Getter
@Setter
public class FaceDao {
    @Autowired
    private RestTemplate restTemplate;

    private String apiKey;
    private String apiSecret;
    private String outerId;

    /**
     * 人脸检测
     */
    public DetectResponseEntity detect(String filePath) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA); // 多部件表单体
        MultipartBodyBuilder multipartBodyBuilder = new MultipartBodyBuilder();
        // ----------------- 表单 part
        multipartBodyBuilder.part("api_key", apiKey);
        multipartBodyBuilder.part("api_secret", apiSecret);
        // ----------------- 文件 part
        // 从磁盘读取文件
        multipartBodyBuilder.part("image_file", new FileSystemResource(String.valueOf(Paths.get(filePath))), MediaType.IMAGE_PNG);
        // build完整的消息体
        MultiValueMap<String, HttpEntity<?>> multipartBody =
                multipartBodyBuilder.build();
        ResponseEntity<DetectResponseEntity> responseEntity =
                restTemplate.postForEntity("https://api-cn.faceplusplus.com/facepp/v3/detect",
                        multipartBody, DetectResponseEntity.class);
        return responseEntity.getBody();
    }

    /**
     * 创建用于登录的人脸faseSet
     */
    public void faceSetCreate() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("api_key", apiKey);
        map.add("api_secret", apiSecret);
        map.add("outer_id", outerId);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        restTemplate.postForEntity("https://api-cn.faceplusplus.com/facepp/v3/faceset/create", request, String.class);
    }

    /**
     * 得到登录人脸FaceSet详细信息
     */
    public FaceSetResponseEntity getFaceSetDetail() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("api_key", apiKey);
        map.add("api_secret", apiSecret);
        map.add("outer_id", outerId);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>
                (map, headers);
        ResponseEntity<FaceSetResponseEntity> responseEntity =
                restTemplate.postForEntity("https://api-cn.faceplusplus.com/facepp/v3/faceset/getdetail", request,
                        FaceSetResponseEntity.class);
        return responseEntity.getBody();
    }

    /**
     * 添加faceToken到FaceSet
     * 人脸标识 faceTokens 组成的字符串，可以是一个或者多个，用逗号分隔。最多不超过5个face_token
     */
    public void addFaceToFaceSet(String faceTokens) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("api_key", apiKey);
        map.add("api_secret", apiSecret);
        map.add("outer_id", outerId);
        map.add("face_tokens", faceTokens);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>
                (map, headers);
        restTemplate.postForEntity("https://api-cn.faceplusplus.com/facepp/v3/faceset/addface", request, String.class);
    }

    /**
     * 人脸比对
     */
    public boolean compareFace(String faceToken1, String faceToken2) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA); // 多部件表单体
        MultipartBodyBuilder multipartBodyBuilder = new MultipartBodyBuilder();
        // ----------------- 表单 part
        multipartBodyBuilder.part("api_key", apiKey);
        multipartBodyBuilder.part("api_secret", apiSecret);
        multipartBodyBuilder.part("face_token1", faceToken1);
        multipartBodyBuilder.part("face_token2", faceToken2);
        //
        // ----------------- 文件 part
        // 从磁盘读取文件
        //multipartBodyBuilder.part("image_file", new FileSystemResource(Paths.get(filePath)), MediaType.IMAGE_PNG);
        // build完整的消息体
        MultiValueMap<String, HttpEntity<?>> multipartBody =
                multipartBodyBuilder.build();
        ResponseEntity<CompareResponseEntity> responseEntity =
                restTemplate.postForEntity("https://api-cn.faceplusplus.com/facepp/v3/compare",
                        multipartBody, CompareResponseEntity.class);
        System.out.println(responseEntity);
        CompareResponseEntity e = responseEntity.getBody();
        if (e.getConfidence() >= e.getThresholds().e5) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 人脸比对返回实体类
     */
    @Data
    public static class CompareResponseEntity {
        private Double confidence;
        private ThresholdsResponseEntity thresholds;
    }

    /**
     * 人脸对比置信度阈值返回实体类
     */
    @Data
    public static class ThresholdsResponseEntity {
        @JsonProperty("1e-5")
        private Double e5;
    }

    /**
     * FaceSet返回实体类
     */
    @Data
    public static class FaceSetResponseEntity {
        private String faceset_token;
        private String outer_id;
        private Integer face_count;
        private List<String> face_tokens;
    }


    /**
     *人脸检测返回数据实体类
     */
    @Data
    public static class DetectResponseEntity {
        private String request_id;
        private Integer face_num;
        private List<FaceEntity> faces;
    }


    /**
     *人脸实体类
     */
    @Data
    public static class FaceEntity {
        private String face_token;
    }
}