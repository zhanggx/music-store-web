package com.example.musicstore.util;

import java.io.File;
import java.io.FileInputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;

public class MD5Util {

	private static String byteArrayToHexString(byte b[]) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++)
			resultSb.append(byteToHexString(b[i]));

		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n += 256;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	public static String MD5Encode(String origin){
		return MD5Encode(origin, "UTF-8");
	}

	public static String MD5Encode(String origin, String charsetname) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			if (charsetname == null || "".equals(charsetname))
				resultString = byteArrayToHexString(md.digest(resultString
						.getBytes()));
			else
				resultString = byteArrayToHexString(md.digest(resultString
						.getBytes(charsetname)));
		} catch (Exception exception) {
		}
		return resultString;
	}
	public static String fileMD5(File inputFile) {
		// 缓冲区大小（这个可以抽出一个参数）
		int bufferSize = 256 * 1024;
		FileInputStream fileInputStream = null;
		DigestInputStream digestInputStream = null;
		try {
			// 拿到一个MD5转换器（同样，这里可以换成SHA1）
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			// 使用DigestInputStream
			fileInputStream = new FileInputStream(inputFile);
			digestInputStream = new DigestInputStream(fileInputStream,messageDigest);
			// read的过程中进行MD5处理，直到读完文件
			byte[] buffer = new byte[bufferSize];
			while (digestInputStream.read(buffer) > 0);
			// 获取最终的MessageDigest
			messageDigest = digestInputStream.getMessageDigest();
			// 拿到结果，也是字节数组，包含16个元素
			byte[] resultByteArray = messageDigest.digest();
			// 同样，把字节数组转换成字符串
			return byteArrayToHexString(resultByteArray);
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			try {
				digestInputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				fileInputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
/*
	public static void main(String[] args) {
        Date dt=new Date(1565790101690L);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dt));

		File file=new File("D:\\projects\\YouKeYunProjects\\EducationSysUpdater\\out\\updater_V1.2.0_R8_201912291740_minrray.apk");
		if (file.exists()) {
			String md5=fileMD5(file);
			System.out.println("file name=" + file.getName() + " length=" + file.length() + " md5=" + md5);
		}else{
			System.out.println("file name=" + file.getName() + " is not exists");
		}
		//file=new File("D:\\projects\\YouKeYunProjects\\replease_out\\youke_V3.1.0_R30.apk");
		file=new File("D:\\projects\\YouKeYunProjects\\android-client-develop\\out\\youke_V3.1.0_R30_201910290917_box_debug.apk");
		if (file.exists()) {
			String md5=fileMD5(file);
			System.out.println("file name=" + file.getName() + " length=" + file.length() + " md5="+  md5);
		}else{
			System.out.println("file name=" + file.getName() + " is not exists");
		}
	}*/
private static final String PASSWORD_PREFIX="youkecloud";
	private static final String PASSWORD_POSTFIX="JustDoIt";
	private static String getMd5Password(String pwd){
		return MD5Util.MD5Encode(PASSWORD_PREFIX + MD5Util.MD5Encode(PASSWORD_PREFIX + pwd + PASSWORD_POSTFIX) + PASSWORD_POSTFIX);
	}
	public static void main(String[] args) {
		String pwd="Cloud2019@)!(";
		String text=getMd5Password(pwd);

		System.out.println("text=" + text);
	}
}
