package annotactionTest;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import annotactionTest.CheckReturnValue.iTtrue;


public class AnnotationTest {

	/**
	 * 自訂的annotation
	 */
	@Retention(RetentionPolicy.RUNTIME)
	public static @interface Debug {
		String value();
	}

	/**
	 * 測試在method上加上annotation
	 */
	@Debug(value = "unicorn")
	@CustomizeAnnotation
	@iTtrue(getfield = "")
	public static String testAnnotation(String str) {
	
		return null;
	}
	
	@Debug(value = "unicorn")
	public Object testField;
	
	/**
	 * 一切都從這開始
	 */
	public static void main(String[] arg) {
		AB ab = new AB();
		Field[] fields = ab.getClass().getFields();
		for(Field f : fields){
			System.out.println("f.getName()=" + f.getName());
		}
	}
	
	public static void findMethodAnnotation(){
		AnnotationTest at = new AnnotationTest();
		Class atClass = at.getClass();
		Method[] method = atClass.getMethods();	// 取出所有class裡的method

		for (Method m : method) {
			boolean isAn = m.isAnnotationPresent(Debug.class);
			System.out.println("m = " + m + " : isAn = " + isAn);
			if (isAn) {
				Debug d = m.getAnnotation(Debug.class);
				System.out.println("de.testValue() = " + d.value());
			}
			
			isAn = m.isAnnotationPresent(CustomizeAnnotation.class);
			System.out.println("m = " + m + " : isAn2 = " + isAn);
			if (isAn) {
				CustomizeAnnotation d = m.getAnnotation(CustomizeAnnotation.class);
				System.out.println("de.testValue2() = " + d.value());
			}
		}
	
	}
	
	public static void findFieldAnnotation(){
		AnnotationTest at = new AnnotationTest();
		Field[] fields = at.getClass().getFields();
		for(Field f : fields){
			System.out.println("f.getName() = " + f.getName());
			if(f.isAnnotationPresent(Debug.class)){
				System.out.println("test Success");
			}
		}
	}

}
