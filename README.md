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

​		![systemConfig](https://github.com/Gaylone/DDNS_for_TencentCloud/blob/main/ReadmeIMG/GetDomainRecordID.png)

​		需要配置的参数请自行到腾讯云DNSpod控制台获取

2.部分参数获取辅助类：GetDomainRecordID

​	路径为：src/main/java/club/sonjong/utils/GetDomainRecordID.java

![GetDomainRecordID](C:\Users\Gaylone\Desktop\新建文件夹\GetDomainRecordID.png)

​		需要配置的参数请自行到腾讯云DNSpod控制台获取

3.邮件发送辅助类：SendEmail

​	路径为：src/main/java/club/sonjong/utils/SendEmail.java

![SendEmail](C:\Users\Gaylone\Desktop\新建文件夹\SendEmail.png)

​		邮件的参数需要填自己的QQ邮箱发送令牌，去qq邮箱申请即可

4.这三个类都有对于腾讯云DNSpod的API做详细解释，请留意
