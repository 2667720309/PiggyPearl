package com.pig.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author Blackke
 */
public class CustomStringUtils {
    public static String getRandomImgName(String fileName) {

        // 获取文件后缀
        int index = fileName.lastIndexOf(".");
        String suffix = fileName.substring(index);

        //检验文件
        if (".jpg".equals(suffix) || ".jpeg".equals(suffix) || ".png".equals(suffix)) {
            //改变上传到服务器的文件名  uuid + suffix
            // 生成UUID
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            return uuid + suffix;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static Set<String> stringToSet(String str, String separator) {
        String[] values = str.split(separator);
        return new HashSet<>(Arrays.asList(values));
    }

    public static String setToString(Set<String> set) {
        return StringUtils.substringBetween(set.toString(), "[", "]");
    }

}
