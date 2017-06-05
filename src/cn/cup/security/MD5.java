package cn.cup.security;

/**
 * Created by Administrator on 2016/11/1.
 */
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 利用JDK提供的MD5算法求一个字节数组或者字符串的消息摘要，并以16进制字符串的形式返回
 *
 * @author jimshen
 *
 */
public class MD5 {
    /**
     * 求一个字节数组的消息摘要，并以16进制字符串的形式返回
     *
     * @param input
     *            要求消息摘要的字节数组
     * @return 消息摘要值，以16进制字符串的形式返回
     * @throws java.security.NoSuchAlgorithmException
     * @throws Exception
     */
    public static String md5(byte[] input) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] b = md.digest(input);
        return conversion(b);
    }

    /**
     * 求一个字符串的消息摘要，并以16进制字符串的形式返回
     *
     * @param
     *
     * @return 消息摘要值，以16进制字符串的形式返回
     * @throws Exception
     * @throws java.io.UnsupportedEncodingException
     * @throws java.security.NoSuchAlgorithmException
     * @throws Exception
     */
    public static String md5(String s) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return md5(s.getBytes("UTF8"));
    }


    /**
     * 将一个字节数组的值转换成16进制字符串
     *
     * @param b
     *            要转换的字节数组
     * @return 转换后的16进制字符串
     */
    private static String conversion(byte[] b) {
        String s = "";
        String[] hex = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a",
                "b", "c", "d", "e", "f" };
        for (int i = 0; i < b.length; i++) {
            int n = b[i];
            if (n < 0)
                n = n + 256;
            int d1 = n / 16;
            int d2 = n % 16;
            s = s + hex[d1] + hex[d2];
        }
        return s;
    }

}
