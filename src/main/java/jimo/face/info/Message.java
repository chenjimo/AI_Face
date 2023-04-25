package jimo.face.info;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/***
 * 封装的与前端的交互信息！
 */
@Data
public class Message {
    private int status;
    private String result;
    private Object data;
    @Value("${face.image-URL}")
    private String imageURL;//静态资源地址

    public Message() {
    }

    public Message(int status, String result, Object data) {
        this.status = status;
        this.result = result;
        this.data = data;
    }
}
