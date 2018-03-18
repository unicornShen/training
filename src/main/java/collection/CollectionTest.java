package collection;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import org.apache.commons.collections4.CollectionUtils;

public class CollectionTest {
	public static void main(String[] args){
		TreeMap<Integer, Object> map = new TreeMap<Integer, Object>();
//		map.put(1, new Object());
//		map.put(2, new Object());
		map.put(3, new Object());
//		map.put(4, new Object());
		map.put(5, new Object());
		map.put(6, new Object());
		
		System.out.println("map.higherKey(3) = " + map.higherKey(3));
		System.out.println("map.lowerKey(3) = " + map.lowerKey(3));
		
		
		testIsEmpty();
	}
	
	private static void testIsEmpty(){
	    List<String> list = new ArrayList<String>();
	    System.out.println("list.isEmpty() = " + list.isEmpty());

	    List<String> list2 = null;
	    System.out.println("CollectionUtils.isEmpty(list2) = " + CollectionUtils.isEmpty(list2));
	}

}
