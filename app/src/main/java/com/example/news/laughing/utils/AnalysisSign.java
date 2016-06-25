package com.example.news.laughing.utils;

import android.util.Log;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.text.Collator;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by Administrator on 2016/6/9.
 */
public class AnalysisSign {
    public static String getsign(String title, int page, int showapi_appid , String showapi_sign)  {
       String s = "page" + page + "showapi_appid" +showapi_appid  + "title" + title + showapi_sign;
        try {
             String str = new String(Hex.encodeHex(DigestUtils.md5(s.getBytes("utf-8"))));
            return str.toUpperCase();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String getImageSign( int page, int showapi_appid , String showapi_sign)  {
        String s = "page" + page + "showapi_appid" +showapi_appid + showapi_sign;
        try {
            String str = new String(Hex.encodeHex(DigestUtils.md5(s.getBytes("utf-8"))));
            return str.toUpperCase();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
