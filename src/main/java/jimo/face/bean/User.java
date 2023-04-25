package jimo.face.bean;

import jdk.nashorn.internal.objects.annotations.Property;
import lombok.Data;

import javax.persistence.*;
@Data
@Table(name = "x_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column
    private String username;
    @Column
    private String email;
    @Column
    @Property
    private String phone;
    @Column
    private String avatar;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }

    public User(int id, String avatar) {
        this.id = id;
        this.avatar = avatar;
    }

    public User() {
    }

    public User(String email) {
        this.email = email;
    }


}
