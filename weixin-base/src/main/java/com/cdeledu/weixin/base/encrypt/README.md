重要说明
------------------------------

>## 2016-07-12
>  + `此目录下所有java源文件均由微信平台官方提供`
>  + `出现异常java.security.InvalidKeyException:illegal Key Size的解决方案 ,由微信平台官方提供`

> >   + `在官方网站下载JCE无限制权限策略文件（JDK7的下载地址：http://www.oracle.com/technetwork/java/javase/downloads/jce-7-download-432124.html)`
> >   + `下载后解压，可以看到local_policy.jar和US_export_policy.jar以及readme.txt`
> >   + `如果安装了JRE，将两个jar文件放到%JRE_HOME%\lib\security目录下覆盖原来的文件`
> >   + `如果安装了JDK，将两个jar文件放到%JDK_HOME%\jre\lib\security目录下覆盖原来文件`
