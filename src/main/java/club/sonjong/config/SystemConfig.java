package club.sonjong.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/***
 * 用于配置腾讯云DNSpod动态解析的参数
 *部分参数不知道，可以运行utils中的GetDomainRecordID.java获取
 *
 * 接口地址：
 *  https://dnsapi.cn/Record.Modify
 *
 * HTTP请求方式：
 * POST
 *
 * 请求参数：
 * 公共参数
 * domain_id 或 domain, 分别对应域名ID和域名, 提交其中一个即可
 * record_id 记录ID，必选
 * sub_domain 主机记录, 如 www，可选，如果不传，默认为 @
 * record_type 记录类型，通过API记录类型获得，大写英文，比如：A，必选
 * record_line 记录线路，通过API记录线路获得，中文，比如：默认，必选
 * record_line_id 线路的ID，通过API记录线路获得，英文字符串，比如：‘10=1’ 【record_line 和 record_line_id 二者传其一即可，系统优先取 record_line_id】
 * value 记录值, 如 IP:200.200.200.200, CNAME: cname.dnspod.com., MX: mail.dnspod.com.，必选
 * mx {1-20} MX优先级, 当记录类型是 MX 时有效，范围1-20, mx记录必选
 * ttl {1-604800} TTL，范围1-604800，不同等级域名最小值不同，可选
 * status [“enable”, “disable”]，记录状态，默认为”enable”，如果传入”disable”，解析不会生效，也不会验证负载均衡的限制，可选
 * weight 权重信息，0到100的整数，可选。仅企业 VIP 域名可用，0 表示关闭，留空或者不传该参数，表示不设置权重信息
 *
 * 响应代码：
 * 共通返回(1 请求成功)
 * -15 域名已被封禁
 * 6 域名ID错误
 * 7 非域名所有者
 * 8 域名无效
 * 17 记录的值不正确
 * 21 域名被锁定
 * 22 子域名不合法
 * 23 子域名级数超出限制
 * 24 泛解析子域名错误
 * 500025 A记录负载均衡超出限制
 * 500026 CNAME记录负载均衡超出限制
 * 26 记录线路错误
 * 27 记录类型错误
 * 30 MX 值错误，1-20
 * 31 没有添加默认线路的记录、存在冲突的记录(A记录、CNAME记录、URL记录不能共存)
 * 32 记录的TTL值超出了限制、NS记录超出限制
 * 33 AAAA 记录数超出限制
 * 34 记录值非法
 * 37 SRV记录超出限制
 * 41 URL的内容不符合DNSPod解析服务条款
 * 82 不能添加黑名单中的IP
 * 104 修改的记录已存在
 * 110 域名没有备案（显性URL和隐形URL类型）
 *
 * ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 *                                                                                                                                     ///
 * 注意事项：                                                                                                                            ///
 * 如果1小时之内，提交了超过5次没有任何变动的记录修改请求，该记录会被系统锁定1小时，不允许再次修改。比如原记录值已经是 1.1.1.1，新的请求还要求修改为 1.1.1.1。    ///
 *                                                                                                                                     ///
 * ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 *
 * 更多：https://www.dnspod.cn/docs/records.html#record-modify
 * */
public class SystemConfig {
    /**
     * TokenID
     * Token
     * domain_name
     * 这三条参数需要自己填写自己的
     * */
    public static final String TokenID="";//tokenid
    public static final String Token="";//token
    public static final String domain_name="";//域名
    public static String recordID="";//记录ID，如果不知道运行utils中的GetDomainRecordID.java获取
    public static String sub_domain="www";//主机记录, 如 www，可选，如果不传，默认为 @
    public static String type="A";//解析类型，详情看域名控制台解析类型列表
    public static String DDNS(String IP) {
        final String[] cmds={"curl","https://dnsapi.cn/Record.Modify","-d","login_token="+TokenID+","+Token+"&format=json&domain="+domain_name+"&record_id="+recordID+"&sub_domain="+sub_domain+"&value="+IP+"&record_type="+type+"&record_line_id=10%3D0"};//最后一个参数是记录线路类型0为电信 1为移动 2位联通
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
            System.out.print("SystemConfig类中的DDNS方法出现IO异常");
            e.printStackTrace();
        }
        return null;

    }


}
