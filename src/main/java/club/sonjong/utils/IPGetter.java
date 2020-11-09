package club.sonjong.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class IPGetter {
/**
 * 公网IP获取类
 * 从第三方IP查询网站获取官网IP
 * **/

public static String getIP() {
    final String[] cmds={"curl","cip.cc"};
    String regex = "\\d+\\.\\d+\\.\\d+\\.\\d+";
    Pattern pattern = Pattern.compile(regex);
    ProcessBuilder process = new ProcessBuilder(cmds);
    Process p;
    try {
        p = process.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream(),"UTF-8"));
        StringBuilder builder = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }
        Matcher matcher = pattern.matcher(builder.toString());

        if (matcher.find()) {
            String ip = matcher.group(0);
            return ip;
        } else {
            return null;
        }


    } catch (IOException e) {
        System.out.print("IPGetter类中的getIP方法发生IO异常！");
        e.printStackTrace();
    }
    return null;

}
}
