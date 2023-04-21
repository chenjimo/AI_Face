package jimo.face.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 调到录入人脸页面
 *
 * @return
 */
@Controller
public class ShouController {
    @RequestMapping("/")
    public String shou() {
        return "face/login";
    }

    @RequestMapping("/in")
    public String input() {
        return "face/input";
    }
}