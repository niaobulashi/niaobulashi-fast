package com.niaobulashi.common.utils;

import com.niaobulashi.common.config.Global;
import com.niaobulashi.common.json.JSON;
import com.niaobulashi.common.json.JSONObject;
import com.niaobulashi.common.utils.http.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: niaobulashi-common
 * @description: 获取地址类
 * @author: 鸟不拉屎 https://niaobulashi.com
 * @create: 2019-08-03 11:34
 */
public class AddressUtils {

    private static final Logger logger = LoggerFactory.getLogger(AddressUtils.class);

    public static final String IP_URL = "http://ip.taobao.com/service/getIpInfo.php";

    /**
     * 根据ip获取区域地址
     * @param ip
     * @return
     */
    public static String getRealAddressByIP(String ip) {
        String address = "XX XX";
        // 判断ip是否为空
        if (StringUtils.isEmpty(ip)) {
            return "ip为空";
        }
        // 内网不查询
        if (IpUtils.internalIp(ip)) {
            return "内网IP";
        }
        // 是否开启获取ip地址开关
        if (Global.isAddressEnabled()) {
            String rspStr = HttpUtils.sendPost(IP_URL, "ip=" + ip);
            if (StringUtils.isEmpty(rspStr)) {
                logger.error("获取地理位置异常{}", ip);
                return address;
            }
            JSONObject obj;
            try {
                obj = JSON.unmarshal(rspStr, JSONObject.class);
                JSONObject data = obj.getObj("data");
                String region = data.getStr("region");
                String city = data.getStr("city");
                address = region + " " + city;
            } catch (Exception e) {
                logger.error("获取地理位置异常 {}", ip);
            }
        }
        return address;
    }
}
