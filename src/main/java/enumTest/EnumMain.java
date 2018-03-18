package enumTest;

import java.util.EnumSet;
import java.util.Iterator;

import org.apache.commons.lang3.EnumUtils;


public class EnumMain {
	public enum Ue{
		a, b, c, d
	}
	
	public static void main(String[] args){
//		EnumMain em = new EnumMain();
//		
//		EnumSet<Ue> enumSet = EnumSet.allOf(Ue.class);
//		
//		em.genEnum(enumSet);
	    
//	    getEnum();
	    
//	    testEnumUtils();
	    
	}
	
	public void genEnum(EnumSet<Ue> e){
		for(Iterator<Ue> it = e.iterator(); it.hasNext();){
			Enum<?> en = (Enum<?>) it.next();
			System.out.println("en.name() = " + en.name());
			System.out.println("en.ordinal() = " + en.ordinal());
		}
	}
	
	public static void getEnum(){
	    for(Ue ue : Ue.values()){
	        System.out.println(String.format("{%s}:{%d}", ue.name(), ue.ordinal()));
	    }
	}
	
	// XXXX
//	public static void testEnumUtils(){
//	    ValuedEnum ve = EnumUtils.getEnum(Ue.class, 0);
//	    System.out.println(ve);	
//	}
}
