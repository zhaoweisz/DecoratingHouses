package yyk.decoratinghouses.util;

import android.util.Log;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class StringUtil {

    public static boolean isEmptyString(String str) {
        if (str != null || !"".equals(str)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * @description 描述:String太长 截取
     */
    public static String omitString(String str, int length) {
        if (str != null && str.length() >= length) {
            return str.substring(0, length) + "...";
        }
        return str;
    }

    /**
     * @description 描述:隐藏名字
     */
    public static String hideName(String str) {
        if (str != null) {
            return str.substring(0, 1) + "**";
        }
        return str;
    }

    /**
     * @description 描述:隐藏部分字符串
     * 185****5638
     */
    public static String hideStringPart(String str, int hideStar, int hideLenth) {
        int length = str.length();
        if (isEmptyString(str)) {
            return str;
        } else {
            if (hideStar + hideLenth > length) {
                hideLenth = length - hideStar;
            }
            String part1 = str.substring(0, hideStar);
            String part2 = "";
            String part3 = str.substring(hideStar + hideLenth);
            for (int i = 0; i < hideLenth; i++) {
                part2 = part2.concat("*");
            }
            str = part1.concat(part2).concat(part3);
            return str;
        }
    }

    /**
     * 数字转换为金额格式
     */
    public static String getFormatMoney(double money) {
        NumberFormat nf = new DecimalFormat("#,###.00");
        String formatMoney = "0.00";
        if (money != 0) {
            formatMoney = nf.format(money);
            if (money < 1 && money > 0) {
                formatMoney = "0" + nf.format(money);
            }
        }
        return formatMoney;
    }

    /**
     * 数字转换为金额格式（没有“，”）
     */
    public static String getFormatMoneyNoComma(double money) {
        NumberFormat nf = new DecimalFormat("####.00");
        String formatMoney = "0.00";
        if (money != 0) {
            formatMoney = nf.format(money);
            if (money < 1) {
                formatMoney = "0" + nf.format(money);
            }
        }
        return formatMoney;
    }

    /**
     * 数字转换为金额格式
     */
    public static String getFormatMoney(String money) {
        if (money == null || money.equals("")) {
            return "0.00";
        }
        Double doubleMoney = Double.valueOf(money);

        return getFormatMoney(doubleMoney);
    }

    /**
     * 返回小数点后面的位数
     */
    public static int getNumberAfterPoint(String str) {
        if (!str.contains(".")) {
            return 0;
        }
        if (str.endsWith(".")) {
            return 0;
        }
        String a[] = str.split("\\.");
        return a[1].length();
    }

    /**
     * 保留小数点后面的length位数
     */
    public static String getStrAfterPoint(String src, int length) {
        if (getNumberAfterPoint(src) < length) {
            return src;
        }
        String res = "";
        String a[] = src.split("\\.");
        String after = a[1].substring(0, length);
        res = a[0].concat(after);
        return res;
    }

    /**
     * 判断密码安全等级
     * 只包含数字1
     * 只包含字母2
     * 字母数字组合3
     */
    public static int passwordSafeLeave(String password) {
        int leave = 0;
        if (isOnlyNumber(password)) {
            leave = 1;
        } else if (isOnlyChar(password)) {
            leave = 2;
        } else if (!isOnlyChar(password) && !isOnlyNumber(password)) {
            leave = 3;
        }
        return leave;
    }

    /**
     * 判是否为纯数字
     */
    public static boolean isOnlyNumber(String str) {
        boolean res = false;
        boolean result = str.matches("[0-9]+");

        if (result == true) {
            res = true;
        } else {
            res = false;
        }
        return res;
    }

    /**
     * 判是否为纯字母
     */
    public static boolean isOnlyChar(String str) {
        boolean res = false;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))) {
                res = true;
            } else {
                res = false;
                return res;
            }
        }
        return res;
    }

    /**
     * 获取红包金额
     */
    public static String getRedPocketMoney(String str) {
        String res = "";
        res = str.substring(0, str.length() - 4);
        return res;
    }

    /**
     * 获取红包类型
     */
    public static String getRedPocketType(String str) {
        String res = "";
        res = str.substring(str.length() - 4, str.length());
        return res;
    }

    /**
     * 去除Html敏感字符
     */
    public static String deleteHtml(String str) {
        String res = "";
        str = str.replaceAll("<", "");
        str = str.replaceAll(">", "");
        str = str.replaceAll("/", "");
        res = str;
        return res;
    }
}
