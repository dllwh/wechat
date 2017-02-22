weinxin-manage
------------------------

>##### 简介
> 1. `weixin-manage`:采用了简洁强大的SpringMVC作为web主框架,数据库现在采用的是mysql,前端jquertEasyUI、bootstrap框架相结合。 
> 2. 后台模块包含：回复管理、意见反馈、图片管理、视频管理、缓存更新、访问统计、组织机构管理、用户管理、角色管理、菜单管理、数据字典管理等功能
> 3. 后端模板支持：bootstrap,Easyui默认样式

>##### 管理地址
> 1. http://${ip:port}/${project_name}/
> 2. http://${ip:port}/${project_name}/loginController.shtml?doLogin

平台部署和配置说明
------------------------

> 1. 下载项目代码，安装jdk 1.7+、maven-3.0.1、mysql-5.6。
> 2. 在项目目录下运行mvn install，提示BUILD SUCCESS即可。
> 3. 创建mysql用户和数据库，运行/weixin-manage/src/main/resource/doc下对应db-wbchat.sql。
> 4. 数据库配置文件：/weixin-manage/src/main/resources/properties/dbConfig.properties


其他说明
------------------------


演示效果截图
------------------------


