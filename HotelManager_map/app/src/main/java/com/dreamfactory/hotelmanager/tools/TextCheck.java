package com.dreamfactory.hotelmanager.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by macair on 16/5/8.
 */
public class TextCheck {
    private static TextCheck ourInstance = new TextCheck();

    public static TextCheck getInstance() {
        return ourInstance;
    }

    private TextCheck() {
    }
    public static boolean isMobileNum(String num){
        Pattern p = Pattern.compile("^(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$");
        Matcher m = p.matcher(num);
        return m.matches();
    }
    public static boolean isEmail(String strEmail) {
        String strPattern = "^[a-zA-Z][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(strEmail);
        return m.matches();
    }
    public static boolean isChineseName(String str) {
        String strPattern = "[\\u4e00-\\u9fa5]{2,}";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(str);
        return m.matches();
    }
    public static boolean isIdNum(String str) {
        String strPattern = "\\d{15}|\\d{18}";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(str);
        return m.matches();
    }
}
