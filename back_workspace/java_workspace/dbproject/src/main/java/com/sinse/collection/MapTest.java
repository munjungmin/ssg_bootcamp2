package com.sinse.collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapTest {
	public static void main(String[] args) {
		//컬렉션 프레임웤 중 순서가 없는 유형 중 Map을 공부한다. 
		Map<String, String> map = new HashMap<>();
		
		map.put("a1", "가나초콜릿");
		map.put("a2", "허쉬");
		map.put("a3", "두바이초콜릿");
		

		Set<String> set = map.keySet();
		Iterator<String> it = set.iterator();
		
		while(it.hasNext()) {
			String key = it.next();
			String value = map.get(key);
			System.out.println("key: " + key + ", value: " + value);
		}
	}
}
