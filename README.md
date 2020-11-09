# 目前您位于本项目的主页，如需访问代码仓库，请将左上角的main切换成master
# ↑master
## 关于项目

本项目是基于JAVA开发的MAVEN工程，是实现腾讯云动态DNS解析的辅助软件。


### 适用场景

开发出来用于普通家庭宽带环境的动态公网IP的DNS解析，虽然少部分人能成功申请到宝贵的宽带运营商的公网IP，但是多数情况下申请到的公网IP会动态变化，且周期不固定。

因此编写一个辅助工具，运用腾讯云免费的DNSpod动态解析API搭配它的域名，可以实现周期性动态解析DNS。

### 使用说明

请自行安装JAVA开发环境，包括jdk1.8，IDEA等支持maven工程的编译器。推荐使用IDEA

#### 参数配置：

​	1.动态DNS解析核心类：SystemConfig

​		路径为src/main/java/club/sonjong/config/SystemConfig.java

​		![systemConfig](https://github.com/Gaylone/DDNS_for_TencentCloud/blob/main/ReadmeIMG/systemConfig.png)

​		需要配置的参数请自行到腾讯云DNSpod控制台获取

2.部分参数获取辅助类：GetDomainRecordID

​	路径为：src/main/java/club/sonjong/utils/GetDomainRecordID.java

![GetDomainRecordID](https://github.com/Gaylone/DDNS_for_TencentCloud/blob/main/ReadmeIMG/GetDomainRecordID.png)

​		需要配置的参数请自行到腾讯云DNSpod控制台获取

3.邮件发送辅助类：SendEmail

​	路径为：src/main/java/club/sonjong/utils/SendEmail.java

![SendEmail](https://github.com/Gaylone/DDNS_for_TencentCloud/blob/main/ReadmeIMG/SendEmail.png)

​		邮件的参数需要填自己的QQ邮箱发送令牌，去qq邮箱申请即可

4.这三个类都有对于腾讯云DNSpod的API做详细解释，请留意
#### 编译成包：

完成上述的参数配置，我们通过IDEA中maven工程将源码打包成jar格式的包

![install](https://github.com/Gaylone/DDNS_for_TencentCloud/blob/main/ReadmeIMG/install.png)

完成后，项目文件夹下会出现target文件夹，里面有对应的jar包

![target](https://github.com/Gaylone/DDNS_for_TencentCloud/blob/main/ReadmeIMG/target.png)

#### 设置开机自启

可选，不过要实现动态代理果然还是需要设置成开机自启动，以Windows为例

1.新建一个txt文件，输入以下内容，然后把txt后缀名改为bat

```bat
@echo "执行DDNS服务"
java -jar F:\Dynspod\DDNS-1.0-SNAPSHOT.jar > F:\Dynspod\ContextLog.log
```

F:\Dynspod\DDNS-1.0-SNAPSHOT.jars 是刚才编译的jar绝对路径

F:\Dynspod\ContextLog.log是要输出的日志文件路径（无需手动创建，每次启动都会清除上次的内容）

此代码表示调用java虚拟机运行jar文件

2.新建一个txt文件，输入以下内容，然后把后缀名改为vbs

```vbscript
Set ws = CreateObject("Wscript.Shell")
ws.run "cmd /c F:\Dynspod\start.bat",0
```

F:\Dynspod\start.bat为第一步编辑的bat文件的绝对路径

此代码表示在没有弹窗的情况下运行此bat文件

3.然后将vbs文件放到

C:\Users\XXXX\AppData\Roaming\Microsoft\Windows\Start Menu\Programs\Startup

XXXX为你的系统账户名称
