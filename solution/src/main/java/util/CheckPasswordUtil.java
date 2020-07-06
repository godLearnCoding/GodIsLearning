package util;

/**
 * 判断密码强度
 */
public class CheckPasswordUtil {

    /**
     * 密码强度
     * Z = 字母 S = 数字 T = 特殊字符
     *
     * @return 0 弱  1 中  2强
     */
    public static int checkPassword(String passwordStr) {
        String regexZ = "\\d*";
        String regexS = "[a-zA-Z]+";
        String regexT = "\\W+$";
        String regexZT = "\\D*";
        String regexST = "[\\d\\W]*";
        String regexZS = "\\w*";
        String regexZST = "[\\w\\W]*";

        if (passwordStr.matches(regexZ)) {
            return 0;
        }
        if (passwordStr.matches(regexS)) {
            return 0;
        }
        if (passwordStr.matches(regexT)) {
            return 0;
        }
        if (passwordStr.matches(regexZT)) {
            return 1;
        }
        if (passwordStr.matches(regexST)) {
            return 1;
        }
        if (passwordStr.matches(regexZS)) {
            return 1;
        }
        if (passwordStr.matches(regexZST)) {
            return 2;
        }
        return -1;
    }
}
