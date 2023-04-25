package jimo.face.bean;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
@Data
@Table(name = "x_face_log")
public class FaceLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "log_time")
    private LocalDateTime logTime;
    @Column(name = "face_url")
    private String faceUrl;
    @Transient
    private String username;
    @Transient
    private String time;
    public FaceLog() {
    }

    public FaceLog(int userId, LocalDateTime logTime, String faceUrl) {
        this.userId = userId;
        this.logTime = logTime;
        this.faceUrl = faceUrl;
    }


    public void setLogTime(LocalDateTime logTime) {
        this.logTime = logTime;
    }

    public void setFaceUrl(String faceUrl) {
        this.faceUrl = faceUrl;
    }


}
