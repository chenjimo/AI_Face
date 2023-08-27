# AI_Face

#### 介绍
通过调用Face++的人脸识别接口的扩展开发与完善！

主要使用Face++的人脸搜素API：
技术文档参考地址：https://console.faceplusplus.com.cn/documents/4888381
前端
1、录入人脸页面
2、打卡人脸页面
3、展示打卡数据页面
后端
逻辑与约定
图片地址选用http://127.0.0.1:9291/img/本地的开放地址
1、录入逻辑不变（先判断库jimo_face内有无，无则录有则不录，判断是否检测人脸获取face_token。然后反馈一个face_token值，并写入outer_id以及对应的user的关联表user_face中）
2、打卡人脸（先搜素库jimo_face内的最高相似度，阈值大于0.65为存在，对比user_face的face_token信息录入face_log中为true否则为false）
3、展示打卡数据页面（多表查询x_user&user_log进行信息展示，分类以及查询页码等）
数据表
face_log（若user_id为-1则为该人脸不存在与系统中打卡失败）

user_face

图片处理https://blog.csdn.net/Muscleheng/article/details/103961840
数据库框架
使用之前Spring Boot Bill 项目的tkmybatis框架实现
记录案例
查询成功返回值
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
复制文件好放式
 Files.copy(dir.toPath(), dest.toPath());
静态资源路径的获取
String staticPath = ClassUtils.getDefaultClassLoader().getResource("static").getPath();

BUG
一、
在这附件有一个input标签获取file失败的问题为解决以及(multipart/form-data)请求问题！

这样请求代替是可以解决的

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
二、
layer的弹窗失效！！！！
考虑引入问题解决。

#### 安装教程

1.  xxxx
2.  xxxx
3.  xxxx

#### 使用说明

1.  xxxx
2.  xxxx
3.  xxxx

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
