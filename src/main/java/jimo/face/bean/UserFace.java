package jimo.face.bean;

import lombok.Data;

import javax.persistence.*;
@Data
@Table(name = "x_user_face")
public class UserFace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "face_token")
    private String faceToken;

    public UserFace() {
    }

    public UserFace(int userId, String faceToken) {
        this.userId = userId;
        this.faceToken = faceToken;
    }

    public UserFace(int id, int userId, String faceToken) {
        this.id = id;
        this.userId = userId;
        this.faceToken = faceToken;
    }

    public void setFaceToken(String faceToken) {
        this.faceToken = faceToken;
    }
}
