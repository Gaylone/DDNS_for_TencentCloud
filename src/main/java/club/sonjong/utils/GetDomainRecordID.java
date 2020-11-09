package club.sonjong.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * 获取记录ID的工具 如果你不知道你的域名里那条指向你的IP的记录的ID，运行这个工具类查看，返回的json格式中有一个参数为records，里面记录了它的ID
 *
 * 接口地址：
 * https://dnsapi.cn/Record.List
 *
 * HTTP请求方式：
 * POST
 *
 * 请求参数：
 * 公共参数
 * domain_id 或 domain, 分别对应域名ID和域名, 提交其中一个即可
 * offset 记录开始的偏移，第一条记录为 0，依次类推，可选（仅当指定 length 参数时才生效）
 * length 共要获取的记录数量的最大值，比如最多获取20条，则为20，最大3000.可选
 * sub_domain 子域名，如果指定则只返回此子域名的记录，可选
 * record_type 记录类型，通过API记录类型获得，大写英文，比如：A，可选
 * record_line 记录线路，通过API记录线路获得，中文，比如：默认，可选
 * record_line_id 线路的ID，通过API记录线路获得，英文字符串，比如：‘10=1’，可选 【需要获取特定线路的解析记录时，record_line 和 record_line_id 二者传其一即可，系统优先取 record_line_id】
 * keyword，搜索的关键字，如果指定则只返回符合该关键字的记录，可选 【指定 keyword 后系统忽略查询参数 sub_domain，record_type，record_line，record_line_id】
 *
 * 响应代码：
 * 共通返回(1 成功)
 * 6 域名ID错误
 * 7 记录开始的偏移无效、非域名所有者
 * 8 共要获取的记录的数量无效、域名无效
 * 13 当前域名有误，请返回重新操作
 * 27 线路无效
 * 注意事项：
 * 如果域名的记录数量超过了100，将会强制分页并且只返回前100条，这时需要通过 offset 和 length 参数去获取其它记录。
 *
 * 更多：https://www.dnspod.cn/docs/records.html#record-modify
 * **/
public class GetDomainRecordID {
    public static final String TokenID="177390";//tokenid
    public static final String Token="8e63b708825d992e2f2ccb7ac5c8854c";//token
    public static final String domain_name="sonjong.club";//我的域名
    public static final String type="A";//记录类型
    public static String getRecordID() {

        final String[] cmds={"curl","https://dnsapi.cn/Record.List","-d","login_token="+TokenID+","+Token+"&format=json&domain="+domain_name+"&record_type="+type};
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
            return builder.toString();

        } catch (IOException e) {
            System.out.print("GetDomainRecordID类中的getRecordID方法出现IO异常");
            e.printStackTrace();
        }
        return null;

    }
//反注释一下内容运行获取部分未知参数
//    public static void main(String[] args) {
//        System.out.println(GetDomainRecordID.getRecordID());
//    }
}
