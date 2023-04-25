package jimo.face.controller;

import jimo.face.bean.FaceLog;
import jimo.face.dao.FaceLogMapper;
import jimo.face.dao.UserMapper;
import jimo.face.info.Message;
import jimo.face.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/***
 * 主要用于对打卡记录的获取操作！！！
 */
@Controller
@ResponseBody
@RequestMapping("/log/")
public class FaceLogController {
    @Autowired
    private FaceLogMapper faceLogMapper;
    @Autowired
    private UserMapper userMapper;
    /***
     * 获取全部打卡信息记录
     * @return
     */
    @PostMapping("all")
    public Message getLogs() {
        List<FaceLog> newfaceLogs = new ArrayList<>();
        faceLogMapper.selectAll().forEach(faceLog -> {
            if (faceLog.getUserId()>0){
                faceLog.setUsername(userMapper.selectByPrimaryKey(faceLog.getUserId()).getUsername());
            }else {faceLog.setUsername("用户不存在");}
            faceLog.setTime(DateUtil.localDateTimeToString(faceLog.getLogTime()));
            newfaceLogs.add(faceLog);
        });
        return new Message(200,"刷新成功！",newfaceLogs);
    }
}
