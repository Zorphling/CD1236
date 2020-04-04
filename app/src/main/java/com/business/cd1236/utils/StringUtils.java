package com.business.cd1236.utils;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    private StringUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 判断字符串是否为null或长度为0
     *
     * @param s 待校验字符串
     * @return {@code true}: 空<br> {@code false}: 不为空
     */
    public static boolean isEmpty(CharSequence s) {
        return s == null || s.length() == 0;
    }

    /**
     * 判断字符串是否为null或全为空格
     *
     * @param s 待校验字符串
     * @return {@code true}: null或全空格<br> {@code false}: 不为null且不全空格
     */
    public static boolean isSpace(String s) {
        return (s == null || s.trim().length() == 0);
    }

    /**
     * 判断两字符串是否相等
     *
     * @param a 待校验字符串a
     * @param b 待校验字符串b
     * @return {@code true}: 相等<br>{@code false}: 不相等
     */
    public static boolean equals(CharSequence a, CharSequence b) {
        if (a == b) return true;
        int length;
        if (a != null && b != null && (length = a.length()) == b.length()) {
            if (a instanceof String && b instanceof String) {
                return a.equals(b);
            } else {
                for (int i = 0; i < length; i++) {
                    if (a.charAt(i) != b.charAt(i)) return false;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 判断两字符串忽略大小写是否相等
     *
     * @param a 待校验字符串a
     * @param b 待校验字符串b
     * @return {@code true}: 相等<br>{@code false}: 不相等
     */
    public static boolean equalsIgnoreCase(String a, String b) {
        return (a == b) || (b != null) && (a.length() == b.length()) && a.regionMatches(true, 0, b, 0, b.length());
    }

    /**
     * null转为长度为0的字符串
     *
     * @param s 待转字符串
     * @return s为null转为长度为0字符串，否则不改变
     */
    public static String null2Length0(String s) {
        return s == null ? "" : s;
    }

    /**
     * 返回字符串长度
     *
     * @param s 字符串
     * @return null返回0，其他返回自身长度
     */
    public static int length(CharSequence s) {
        return s == null ? 0 : s.length();
    }

    /**
     * 首字母大写
     *
     * @param s 待转字符串
     * @return 首字母大写字符串
     */
    public static String upperFirstLetter(String s) {
        if (isEmpty(s) || !Character.isLowerCase(s.charAt(0))) return s;
        return String.valueOf((char) (s.charAt(0) - 32)) + s.substring(1);
    }

    /**
     * 首字母小写
     *
     * @param s 待转字符串
     * @return 首字母小写字符串
     */
    public static String lowerFirstLetter(String s) {
        if (isEmpty(s) || !Character.isUpperCase(s.charAt(0))) {
            return s;
        }
        return String.valueOf((char) (s.charAt(0) + 32)) + s.substring(1);
    }

    /**
     * 反转字符串
     *
     * @param s 待反转字符串
     * @return 反转字符串
     */
    public static String reverse(String s) {
        int len = length(s);
        if (len <= 1) return s;
        int mid = len >> 1;
        char[] chars = s.toCharArray();
        char c;
        for (int i = 0; i < mid; ++i) {
            c = chars[i];
            chars[i] = chars[len - i - 1];
            chars[len - i - 1] = c;
        }
        return new String(chars);
    }

    /**
     * 转化为半角字符
     *
     * @param s 待转字符串
     * @return 半角字符串
     */
    public static String toDBC(String s) {
        if (isEmpty(s)) {
            return s;
        }
        char[] chars = s.toCharArray();
        for (int i = 0, len = chars.length; i < len; i++) {
            if (chars[i] == 12288) {
                chars[i] = ' ';
            } else if (65281 <= chars[i] && chars[i] <= 65374) {
                chars[i] = (char) (chars[i] - 65248);
            } else {
                chars[i] = chars[i];
            }
        }
        return new String(chars);
    }

    /**
     * 转化为全角字符
     *
     * @param s 待转字符串
     * @return 全角字符串
     */
    public static String toSBC(String s) {
        if (isEmpty(s)) {
            return s;
        }
        char[] chars = s.toCharArray();
        for (int i = 0, len = chars.length; i < len; i++) {
            if (chars[i] == ' ') {
                chars[i] = (char) 12288;
            } else if (33 <= chars[i] && chars[i] <= 126) {
                chars[i] = (char) (chars[i] + 65248);
            } else {
                chars[i] = chars[i];
            }
        }
        return new String(chars);
    }

    /**
     * 将text放到剪贴板
     *
     * @param activity
     * @param text
     */
    @SuppressLint("NewApi")
    @SuppressWarnings({"static-access", "deprecation"})
    public static void setClipText(Context activity, String text) {
        ClipboardManager clipboardManager = (ClipboardManager) activity.getSystemService(activity.CLIPBOARD_SERVICE);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            clipboardManager.setText(text);
        } else {
            clipboardManager.setPrimaryClip(ClipData.newPlainText("text", text));
        }
    }

    /**
     * 检查字符串不能为null 或 ""
     * true为空
     */
    public static boolean checkString(String s) {
        if (TextUtils.isEmpty(s) || TextUtils.equals("", s)) {
            return true;
        }
        return false;
    }


    /**
     * 数字大于一定数值后以文字显示，例如10000，以1万显示
     */
    public static String intToString(String s) {
        if (isEmpty(s)) {
            return s;
        }
        String result = "";
        if (s.length() == 5) {
            char a = s.charAt(0);
            result = String.valueOf(a) + "万";
        }
        if (s.length() == 6) {
            char a = s.charAt(0);
            char b = s.charAt(1);
            result = String.valueOf(a) + String.valueOf(b) + "万";
        }
        if (s.length() == 7) {
            char a = s.charAt(0);
            char b = s.charAt(1);
            char c = s.charAt(2);
            result = String.valueOf(a) + String.valueOf(b) + String.valueOf(c) + "万";
        }
        if (s.length() == 8) {
            char a = s.charAt(0);
            char b = s.charAt(1);
            char c = s.charAt(2);
            char d = s.charAt(3);
            result = String.valueOf(a) + String.valueOf(b) + String.valueOf(c) + String.valueOf(d) + "万";
        }
        if (s.length() == 9) {
            char a = s.charAt(0);
            result = String.valueOf(a) + "亿";
        }
        if (s.length() == 10) {
            char a = s.charAt(0);
            char b = s.charAt(1);
            result = String.valueOf(a) + String.valueOf(b) + "亿";
        }
        return result;
    }

    /**
     * 保留两位小数正则
     * @param number
     * @return
     */
    public static boolean isOnlyPointNumber(String number) {
        Pattern pattern = Pattern.compile("^\\d+\\.?\\d{0,2}$");
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }

     /**
     * 将银行卡中间八个字符隐藏为*
     */
    public static String getHideBankCardNum(String bankCardNum) {
        int length = bankCardNum.length();

        if (length > 4) {
            String startNum = bankCardNum.substring(0, 4);
            String endNum = bankCardNum.substring(length - 4, length);
            bankCardNum = startNum + " **** **** " + endNum;
        }
        return bankCardNum;
    }

    /**
     * 判断字符串a是否包含字符串b
     *
     * @param a 包含字符串（原字符串）
     * @param b 被包含字符串（比较的字符串）
     * @return
     */
    public static boolean isAIncludeB(String a, String b) {
        if (a.indexOf(b) != -1) {
            return true;
        }
        return false;

    }

    //得到EditText输入的
    public static String getEditText(EditText editText){
        return editText.getText().toString().trim();
    }

}
