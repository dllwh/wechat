Shiro框架简单介绍
------------------------------
	Apache Shiro是Java的一个安全框架，旨在简化身份验证和授权。Shiro在JavaSE和JavaEE项目中都可以使用。它主要用来处理身份认证，授权，企业会话管理和加密等.Shiro的具体功能点如下:
	
> * `身份认证/登录，验证用户是不是拥有相应的身份`

> * `授权，即权限验证，验证某个已认证的用户是否拥有某个权限；即判断用户是否能做事情`

> * `会话管理，即用户登录后就是一次会话，在没有退出之前，它的所有信息都在会话中`

> * `加密，保护数据的安全性`

> * `Web支持，可以非常容易的集成到Web环境`

> * `-- 未完待续 -- `
		
	其核心组件是Subject,SecurityManger、Realms
		
> * `Subject:即”当前操作用户“`

> * `securityManage:这是shiro框架的核心`

> * `Realm实际上就是Shiro与应用安全数据之间交互的桥梁，即对用户进行认证、授权验证的时候，Shiro从应用配置的Realm查询用户以及权限信息，而且，shiro实质上是一个安全相关的Dao,其封装了数据源相关的连接，并在需要时将数据提供给Shiro。一个项目中可以配置多个Realm,但至少有一个Realm`


Shiro 过滤器 说明
------------------------------
![image](https://raw.githubusercontent.com/dllwh/wechat/master/static/wechat-manage/shiroFilter.png)



SHiro 标签说明
------------------------------

>#####  在使用Shiro标签库前，首先需要在JSP引入shiro标签：

	<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

>#####  1. guest标签 ：验证当前用户是否为“访客”，即未认证（包含未记住）的用户
	<shiro:guest>
		Hi there!  Please <a href="login.jsp">Login</a> or <a href="signup.jsp">Signup</a> today! 
	</shiro:guest>

>#####  2. user标签 ：认证通过或已记住的用户
	<shiro:user> 
		Welcome back John!  Not John? Click <a href="login.jsp">here<a> to login. 
	</shiro:user>

>##### 3. authenticated标签 ：已认证通过的用户。不包含已记住的用户，这是与user标签的区别所在
	<shiro:authenticated> 
		<a href="updateAccount.jsp">Update your contact information</a>. 
	</shiro:authenticated>

>#####  notAuthenticated标签 ：未认证通过用户，与authenticated标签相对应。与guest标签的区别是，该标签包含已记住用户
	<shiro:notAuthenticated> 
		lease <a href="login.jsp">login</a> in order to update your credit card information. 
	</shiro:notAuthenticated>

>##### 5. principal 标签 ：输出当前用户信息，通常为登录帐号信息
> >		Hello, <shiro:principal/>, how are you today?

>##### 6. hasRole标签 ：验证当前用户是否属于该角色
	<shiro:hasRole name="administrator"> 
	<a href="admin.jsp">Administer the system</a> 
	</shiro:hasRole>

>##### 7. lacksRole标签 ：与hasRole标签逻辑相反，当用户不属于该角色时验证通过
	<shiro:lacksRole name="administrator"> 
		Sorry, you are not allowed to administer the system.  
	</shiro:lacksRole>

>##### 8. hasAnyRole标签 ：验证当前用户是否属于以下任意一个角色
	<shiro:hasAnyRoles name="developer, project manager, administrator"> 
		You are either a developer, project manager, or administrator. 
	</shiro:lacksRole>

>##### 9. hasPermission标签 ：验证当前用户是否拥有指定权限
	<shiro:hasPermission name="user:create"> 
		<a href="createUser.jsp">Create a new User</a> 
	</shiro:hasPermission>

>##### 10. lacksPermission标签 ：与hasPermission标签逻辑相反，当前用户没有制定权限时，验证通过。
	<shiro:hasPermission name="user:create"> 
		<a href="createUser.jsp">Create a new User</a> 
	</shiro:hasPermission>