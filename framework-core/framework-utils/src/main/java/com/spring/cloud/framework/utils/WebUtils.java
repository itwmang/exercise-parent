
package com.spring.cloud.framework.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 格式转换工具类
 */
public class WebUtils {
    public static Integer parseStrToInteger(String intId, Integer defaultIntId) {
        if (StringUtils.isEmpty(intId)) {
            return defaultIntId;
        } else {
            try {
                return Integer.parseInt(intId.trim());
            } catch (Exception var3) {
                return defaultIntId;
            }
        }
    }

    public static Long parseStrToLong(String intId, Long defaultIntId) {
        if (StringUtils.isEmpty(intId)) {
            return defaultIntId;
        } else {
            try {
                return Long.parseLong(intId.trim());
            } catch (Exception var3) {
                return defaultIntId;
            }
        }
    }

    public static Double parseStrToDouble(String intId, Double defaultDoubleId) {
        if (StringUtils.isEmpty(intId)) {
            return new Double(0.0D);
        } else {
            try {
                return Double.parseDouble(intId.trim());
            } catch (Exception var3) {
                return new Double(0.0D);
            }
        }
    }

    public static String buildURLEncoder(String param) {
        if (StringUtils.isEmpty(param)) {
            return "";
        } else {
            try {
                return URLEncoder.encode(param, "UTF-8");
            } catch (UnsupportedEncodingException var2) {
                var2.printStackTrace();
                return "";
            }
        }
    }

    public static String buildURLDecoder(String param) {
        try {
            return URLDecoder.decode(param, "UTF-8");
        } catch (UnsupportedEncodingException var2) {
            var2.printStackTrace();
            return "";
        }
    }

    public static boolean isWxRequest(String userAgent) {
        return userAgent.indexOf("micromessenger") > -1;
    }

    private WebUtils() {
    }
}
