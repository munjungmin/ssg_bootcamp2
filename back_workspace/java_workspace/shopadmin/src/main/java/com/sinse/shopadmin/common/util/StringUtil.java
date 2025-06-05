package com.sinse.shopadmin.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringUtil {

	public static String getSecuredPass(String pass){
		StringBuffer sb = null;
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] hash = md.digest(pass.getBytes("UTF-8"));
			
			// 잘게 쪼갠 바이트를 16진수 문자열로 변환 
			sb = new StringBuffer(); //String의 불변적 특징을 해결한 객체
			for(int i = 0; i < hash.length; i++) {
				// byte 데이터를 16진수로 변경하는 과정에서, byte 값 안에 음수가 존재할 경우 
				// byte형이 int형으로 변경되면서 부호비트가 붙는데, 이 부호비트는 암호화에 사용되지 않으므로 제거
				String hex = Integer.toHexString(0xff & hash[i]);  //음수 제거 연산
				if(hex.length() < 2) sb.append("0");
				sb.append(hex);
			}
		
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {  // 예외별로 다르게 처리할거면 따로 씀
			e.printStackTrace();
		}
		
		return sb.toString();
	}
}
