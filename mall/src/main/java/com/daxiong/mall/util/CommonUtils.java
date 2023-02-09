package com.daxiong.mall.util;

import com.daxiong.mall.common.Constant;
import com.daxiong.mall.model.pojo.User;
import jakarta.servlet.http.HttpSession;
import org.apache.tomcat.util.codec.binary.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 通用工具类
 */
public class CommonUtils {

    /**
     * 获取当前用户的id
     * @param session
     * @return
     */
    public static Integer getCurrentUserId(HttpSession session) {
        return ((User) session.getAttribute(Constant.CURRENT_MALL_USER)).getId();
    }

    /**
     * 加密方法，使用 MD5（hash）方法，不能够解密
     * @param strValue
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String getMD5Str(String strValue) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        return Base64.encodeBase64String(md5.digest((strValue + Constant.SALT).getBytes()));
    }

}
