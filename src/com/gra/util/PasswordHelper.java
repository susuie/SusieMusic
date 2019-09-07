package com.gra.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 对密码进行MD5加密
 * @author curry
 *
 */
public class PasswordHelper {

	/**
	 * 进行MD5/SHA加密 
	 * @param str要加密的信息
	 * 
	 */
	public static String encryptToMD5(String str) {
		byte[] byteArray = null;
		try {
			// 得到一个MD5的消息摘要
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			
			// 得到一个SHA-1的消息摘要
			// messageDigest = MessageDigest.getInstance("SHA-1");
			// messageDigest.reset();
			// 添加要进行计算摘要的信息
			messageDigest.update(str.getBytes());
			// messageDigest.update(str.getBytes("UTF-8"));
			// 得到该摘要
			byteArray = messageDigest.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		// 将摘要转为字符串 返回
		return byte2hex(byteArray);
	}

	/**
	 * 将二进制转化为16进制字符串
	 *
	 * @param b 二进制字节数组
	 * @return String
	 */
	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int i = 0; i < b.length; i++) {
			stmp = (java.lang.Integer.toHexString(b[i] & 0XFF));
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
		}
		return hs.toUpperCase();
	}

	/**
	 * 测试方法
	 * @param args
	 */
	public static void main(String[] args) {
		String msg = "Hello";
		System.out.println("明文是：" + msg);
		// 执行MD5加密"Hello"
		System.out.println("MD5 加密了是：" + new PasswordHelper().encryptToMD5(msg));
		// 8B1A9953C4611296A827ABF8C47804D7
	}

}
