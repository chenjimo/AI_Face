# AI_Face

#### 介绍
通过调用Face++的人脸识别接口的扩展开发与完善！
原开发技术文档：[https://www.yuque.com/jimoworld/javabj/gcozz94xeg3qb68s](https://www.yuque.com/jimoworld/javabj/gcozz94xeg3qb68s)

:::success
主要使用Face++的人脸搜素API：<br />技术文档参考地址：[Face⁺⁺](https://console.faceplusplus.com.cn/documents/4888381)
:::
<a name="zXyFC"></a>
### 前端
> 1、录入人脸页面
> 2、打卡人脸页面
> 3、展示打卡数据页面

<a name="rIMsZ"></a>
### 后端
<a name="mCDxy"></a>
#### 逻辑与约定
> 图片地址选用http://127.0.0.1:9291/img/本地的开放地址

1、录入逻辑不变（先判断库jimo_face内有无，无则录有则不录，判断是否检测人脸获取**_face_token_**。然后反馈一个**_face_token_**值，并写入outer_id以及对应的user的关联表user_face中）<br />2、打卡人脸（先搜素库jimo_face内的最高相似度，阈值大于0.65为存在，对比user_face的**_face_token_**信息录入face_log中为true否则为false）<br />3、展示打卡数据页面（多表查询x_user&user_log进行信息展示，分类以及查询页码等）
<a name="wolCH"></a>
#### 数据表
face_log（若user_id为-1则为该人脸不存在与系统中打卡失败）<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/26820301/1682089881189-ba4bd9e1-0128-48e0-b428-6815e2466a4f.png#averageHue=%23f6f5f4&clientId=uc58266ff-02f0-4&from=paste&height=52&id=u145da4fb&originHeight=60&originWidth=300&originalType=binary&ratio=1.149999976158142&rotation=0&showTitle=false&size=2323&status=done&style=none&taskId=u9c447526-6a41-4b4e-8c42-61720e4d7e3&title=&width=260.8695706257524)<br />user_face<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/26820301/1682089910520-f7e5ff68-d21a-476b-a5d0-976b6604aa0d.png#averageHue=%23f4f3f2&clientId=uc58266ff-02f0-4&from=paste&height=52&id=u7c5e71ed&originHeight=60&originWidth=226&originalType=binary&ratio=1.149999976158142&rotation=0&showTitle=false&size=2115&status=done&style=none&taskId=ub1738e61-0697-493d-94fa-94bd0975b9a&title=&width=196.52174320473347)
<a name="p7DQz"></a>
#### 图片处理
[springboot保存图片到项目文件资源路径_springboot保存图片到项目路径_Muscleheng的博客-CSDN博客](https://blog.csdn.net/Muscleheng/article/details/103961840)
<a name="d2n71"></a>
#### 数据库框架
使用之前Spring Boot Bill 项目的tkmybatis框架实现
<a name="qD5nH"></a>
### 记录案例
<a name="dk41P"></a>
#### 查询成功返回值
```json
{
    "faceset_token": "fad11cf68883216affac15468bd99ca8",
    "tags": "",
    "time_used": 86,
    "user_data": "",
    "display_name": "JIMO测试人脸库",
    "face_tokens": [
        "470a39af7fa9c56e8a6a9022427abced",
        "25483e8acc79671310dd6ac5f5680b55",
        "8ab79e098c99f2af2aa71a7919ba1227"
    ],
    "face_count": 3,
    "request_id": "1682173703,75826b2d-bc36-4aaf-ad90-9cee331b8f5a",
    "outer_id": "jimo_face"
}
```
<a name="r4mqu"></a>
#### 复制文件好放式
` Files.copy(dir.toPath(), dest.toPath());`
<a name="NheWS"></a>
#### 静态资源路径的获取
`String staticPath = ClassUtils.getDefaultClassLoader().getResource("static").getPath();`

<a name="M0eYK"></a>
### BUG
<a name="wgwIg"></a>
#### 一、
> 在这附件有一个input标签获取file失败的问题为解决以及(multipart/form-data)请求问题！

![image.png](https://cdn.nlark.com/yuque/0/2023/png/26820301/1682266642688-a0336c0c-9f7b-47c2-9bec-13afc099d846.png#averageHue=%23383633&clientId=u6722da81-6717-4&from=paste&height=790&id=u5579050f&originHeight=909&originWidth=1228&originalType=binary&ratio=1.149999976158142&rotation=0&showTitle=false&size=1332101&status=done&style=none&taskId=u6cfc6c97-dbc6-4a28-b1d0-8c224aac6aa&title=&width=1067.8261090947465)<br />这样请求代替是可以解决的<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/26820301/1682266722218-8fa8155b-7fcf-4f54-8a74-3996a6eb058c.png#averageHue=%2322272e&clientId=u6722da81-6717-4&from=paste&height=798&id=u8ea76cce&originHeight=918&originWidth=923&originalType=binary&ratio=1.149999976158142&rotation=0&showTitle=false&size=65976&status=done&style=none&taskId=u8f42e7a0-3da5-44b6-a0e0-db14a9a7ab6&title=&width=802.6087122918982)
```javascript
var form = new FormData();
form.append("file", fileInput.files[0], "<file>");
form.append("id", "<id>");

var settings = {
    "url": "http://127.0.0.1127.0.0.1:9291/admin/uploadImage",
    "method": "POST",
    "timeout": 0,
    "headers": {
        "User-Agent": "apifox/1.0.0 (https://www.apifox.cn)"
    },
    "processData": false,
    "mimeType": "multipart/form-data",
    "contentType": false,
    "data": form
};

$.ajax(settings).done(function (response) {
    console.log(response);
});
```
<a name="An9QK"></a>
#### 二、
layer的弹窗失效！！！！<br />考虑引入问题解决。


#### 安装教程

1.  直接拉去项目到自己的IDEA上或直接下载压缩包再解压用IDEA打开均可。
2.  修改YML配置文档中API密钥与ID(Face++免费注册使用：https://console.faceplusplus.com.cn/register)，和数据等参数https://gitee.com/chenjimo/AI_Face/blob/FaceController/src/main/resources/application-dev.yml
3.  创建对应的数据库文件，并写入数据脚本：https://gitee.com/chenjimo/AI_Face/blob/FaceController/faceController.sql
4.  启动FaceApplication，通过端口号9219直接进入项目首页：http://127.0.0.1:9291/

#### 使用说明

1.  可录入人脸进入自己的库信息。(http://127.0.0.1:9291/in)要根据邮箱信息进行录入。先要在数据库中设置用户信息
2.  进行打卡测试。（http://127.0.0.1:9291/）
3.  展示页面可以展示自己的刷脸打卡记录信息。（http://127.0.0.1:9291/log/）部分筛选功能尚未完成。

#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


#### 特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  Gitee 官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解 Gitee 上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是 Gitee 最有价值开源项目，是综合评定出的优秀开源项目
5.  Gitee 官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  Gitee 封面人物是一档用来展示 Gitee 会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
