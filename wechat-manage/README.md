weinxin-manage
------------------------

>##### 简介

> 1. `wechat-manage` 是基于SpringMVC框架的权限管理系统,支持操作权限和数据权限，后台管理采用的是Spring、Spring MVC、Apache Shiro、Mybatis等，数据库现在采用的是mysql,前端采用jquertEasyUI、bootstrap框架、bootstrap-table相结合。
> 2. 后台模块包含：回复管理、意见反馈、图片管理、视频管理、缓存更新、访问统计、用户管理、角色管理、菜单管理、数据字典管理等功能
> 3. 后端模板支持：bootstrap,Easyui默认样式

[技术选型](http://note.youdao.com/noteshare?id=233df4488c0cac1b719952432977ca74&sub=CD29D21A0EA0410F83F9447BB3D1F0B9)
------------------------

[命名规范](http://note.youdao.com/noteshare?id=edb0df577a78a28d3c44cc8778b74149&sub=5F205BB8938F437CB2CF7F02ED11AD71)
------------------------


平台部署和配置说明
------------------------

> 1. 下载项目代码，安装jdk 1.7+、maven-3.0.1、mysql-5.6。
> 2. 在项目目录下运行mvn install，提示BUILD SUCCESS即可。
> 3. 创建mysql用户和数据库，运行/weixin-manage/src/main/resource/doc下对应db-wbchat.sql。
> 4. 数据库配置文件：/wechat-manage/src/main/resources/properties/dbConfig.properties


其他说明
------------------------

>##### 应用分层（参考阿里巴巴Java开发手册）
![image](https://raw.githubusercontent.com/dllwh/wechat/master/static/0.png)


演示效果截图
------------------------


