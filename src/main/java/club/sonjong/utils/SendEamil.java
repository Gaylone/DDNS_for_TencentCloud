package club.sonjong.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import com.sun.mail.util.MailSSLSocketFactory;
/**
 * 邮件发送工具
 * 用QQ邮箱的SMTP服务
 *当IP发生改变时，会有一封邮件发送到你的邮箱内，帮助你确认，以防本程序没有自动帮你更改解析，你可以通过改邮件内容手动更改
 * hostAddress
 * receiver
 * code
 * 这些参数需要填写自己对应的码
 * 本项目用qq邮箱，如果选用其他邮箱请根据对应的手册修改代码
 * **/
public class SendEamil {
    private static String hostAddress="";//发送者
    private static String receiver="";//接收者
    private static String code="";//授权码
     public static void mailConfg(String content) throws GeneralSecurityException, MessagingException {
         Properties properties = new Properties();
         properties.setProperty("mail.host","smtp.qq.com");//主机地址
         properties.setProperty("mail.transport.protocol","smtp");//协议类型
         properties.setProperty("mail.smtp.auth","true");
         //QQ存在一个特性设置SSL加密
         MailSSLSocketFactory sf = new MailSSLSocketFactory();
         sf.setTrustAllHosts(true);
         properties.put("mail.smtp.ssl.enable", "true");
         properties.put("mail.smtp.ssl.socketFactory", sf);
         //创建一个session对象
         Session session = Session.getDefaultInstance(properties, new Authenticator() {
             @Override
             protected PasswordAuthentication getPasswordAuthentication() {
                 return new PasswordAuthentication(hostAddress,code);//配置你自己的邮箱
             }
         });


         //获取连接对象
         Transport transport = session.getTransport();

         //连接服务器
         transport.connect("smtp.qq.com",hostAddress,code);

         //创建邮件对象
         MimeMessage mimeMessage = new MimeMessage(session);

         //邮件发送人
         mimeMessage.setFrom(new InternetAddress(hostAddress));

         //邮件接收人
         mimeMessage.setRecipient(Message.RecipientType.TO,new InternetAddress(receiver));

         //邮件标题
         mimeMessage.setSubject("您的服务器IP变动提醒");

         //邮件内容
         mimeMessage.setContent("你的服务器IP已变更，新的IP为:"+content,"text/html;charset=UTF-8");

         //发送邮件
         transport.sendMessage(mimeMessage,mimeMessage.getAllRecipients());

         //关闭连接
         transport.close();
     }

}
