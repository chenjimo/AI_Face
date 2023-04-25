package jimo.face.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面资源显示控制流转
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
        return "face/in";
    }

    @RequestMapping("/log")
    public String log() {
        return "face/log";
    }
}