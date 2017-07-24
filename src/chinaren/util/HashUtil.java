package chinaren.util;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.util.Random;

/**
 * 关于哈希加密的工具类
 * @ClassName HashUtil
 * @author 李浩然
 * @date 2017年7月20日
 * @version 1.0
 */
public class HashUtil {
    /**
     * 生成含有随机盐的密码
     * @author 李浩然
     * @param password 待转换的原始密码
     * @return 加盐哈希处理后的密码
     */
    public static String generate(String password) {
        Random r = new Random();
        StringBuilder sb = new StringBuilder(16);
        sb.append(r.nextInt(99999999)).append(r.nextInt(99999999));
        int len = sb.length();
        if (len < 16) {
            for (int i = 0; i < 16 - len; i++) {
                sb.append("0");
            }
        }
        String salt = sb.toString();
        password = md5Hex(password + salt);
        char[] cs = new char[48];
        for (int i = 0; i < 48; i += 3) {
            cs[i] = password.charAt(i / 3 * 2);
            char c = salt.charAt(i / 3);
            cs[i + 1] = c;
            cs[i + 2] = password.charAt(i / 3 * 2 + 1);
        }
        return new String(cs);
    }

    /**
     * 
     * 校验密码是否正确
     * @author 李浩然
     * @param password 待验证的原始密码
     * @param md5 待匹配的哈希值
     * @return 若匹配成功，返回true；否则，返回false
     */
    public static boolean verify(String password, String md5) {
        char[] cs1 = new char[32];
        char[] cs2 = new char[16];
        for (int i = 0; i < 48; i += 3) {
            cs1[i / 3 * 2] = md5.charAt(i);
            cs1[i / 3 * 2 + 1] = md5.charAt(i + 2);
            cs2[i / 3] = md5.charAt(i + 1);
        }
        String salt = new String(cs2);
        return md5Hex(password + salt).equals(new String(cs1));
    }

    /**
     * 从哈希值从提取盐
     * @author 李浩然
     * @param md5 待提取的哈希值字符串
     * @return 提取出的盐
     */
    public static String getSalt(String md5) {
        char[] salt = new char[16];
        for (int i = 0; i < 48; i += 3) {
            salt[i / 3] = md5.charAt(i + 1);
        }
        return new String(salt);
    }

    /**
     * 获取十六进制字符串形式的MD5摘要
     * @author 李浩然
     * @param src 待转换的字符串
     * @return 转换后的字符串
     */
    public static String md5Hex(String src) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bs = md5.digest(src.getBytes());
            return new String(new Hex().encode(bs));
        } catch (Exception e) {
            return null;
        }
    }

    /*public static void main(String[] args) {
        String password = generate("45678901");
        System.out.println(password);
        System.out.println(password.length());
        System.out.println(getSalt(password));
        System.out.println(verify("admin", password));
    }*/

}
