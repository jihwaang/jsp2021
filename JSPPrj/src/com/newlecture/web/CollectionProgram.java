package com.newlecture.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.newlecture.web.entity.Member;

public class CollectionProgram {
	public static void main(String[] args) {
		
		Object[] members = new Object[10];
		members[0] = new Member();
		members[1] = new Member();
		
		((Member)members[0]).getNicName();
		
		List<Member> list = new ArrayList<>();
		list.add(new Member());
		list.add(new Member());
		
		list.get(0).getNicName();
		
		System.out.println(list.get(1));
		
		Set set = new HashSet();
		set.add(10);
		set.add(100);
		set.add("hello");
		set.add(2.3);
		
		System.out.println(100);
		
		Map map = new HashMap();
		map.put("id", 1);
		map.put("title", "hello");
		map.put("hit", 1);
		
		System.out.println(map.get("title"));
	}
}
