package club.sonjong.demo;

import club.sonjong.config.SystemConfig;
import club.sonjong.utils.IPGetter;
import club.sonjong.utils.SendEmail;
import javax.mail.MessagingException;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DDNS_demo {

    public static void main(String[] args) {
        /**
         * 0.启动时获取一次IP,直接解析该IP，然后转入动态解析(返回的json中的code如果为1，即解析成功)
         * 1.设置循环 60s一次
         * 2.比较IP，改变就调用API解析新IP
         **/
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("正在获取本机公网IP...");
        String oldIP= IPGetter.getIP();
        System.out.println("本机公网IP为"+oldIP);
        System.out.println("直接解析当前IP...");
        System.out.println(SystemConfig.DDNS(oldIP));
        System.out.println("当前IP解析完成，约1分钟后转入动态解析模式...");
        int count = 0;
        while(true) {
            try {
                Thread.sleep(60 * 1000); //设置暂停的时间 60 秒
                String newIP= IPGetter.getIP();
                count ++ ;
                System.out.println(sdf.format(new Date()) + "--动态解析监测第" + count + "次。当前IP:"+newIP);
                if (!(newIP.equals(oldIP))) {
                    System.out.println("注意！IP发生改变！新的公网IP为"+newIP+",正在为您动态解析新IP...");
                    //不管解析结果如何都调用邮件函数提醒用户
                    SendEmail.mailConfg(newIP+"\n"+sdf.format(new Date()));
                    System.out.println(SystemConfig.DDNS(newIP));
                    oldIP=newIP;//重新更新旧IP
                    System.out.println("当前本机IP为"+oldIP);
                }
            } catch (InterruptedException e) {
                System.out.print("DDNS_demo类中的main方法中的循环解析发生Interrupted异常！该异常由线程定时器引起...");
                e.printStackTrace();
            } catch (GeneralSecurityException e) {
                System.out.print("DDNS_demo类中的main方法中的循环解析发生GeneralSecurity异常！该异常由邮件发送组件引起...");
                e.printStackTrace();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }

    }
}

