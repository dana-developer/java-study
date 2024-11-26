package collection;

import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;

public class LinkedListTest {

	public static void main(String[] args) {
		List<String> list = new LinkedList<>();
		
		list.add("둘리");
		list.add("마이콜");
		list.add("또치");
		
		// 순회 1
		for(int i=0; i<list.size(); i++) {
			String s = list.get(i);
			System.out.println(s);
		}
		
		// 삭제
		list.remove(2);
		
		// 순회 2
		Iterator<String> it = list.iterator(); //Enumeration가 iterator 역할
		
		while(it.hasNext()) {
			String s = it.next();
			System.out.println(s);
		}
		
		// 순회
		for(String s : list) {
			System.out.println(s);
		}
	}

}
