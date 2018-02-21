package objectTest;

public class ObjectTest {
	public static void main(String[] arg) {
		testReference(new ObjectA(1, "ONE"));
	}

	public static void testReference(Object oo) {
		// 因為無法在此知道傳進來的物件是屬於哪種
		// 因此也無法直接使用下面的方法
		// 但是可以用繼承或介面的方式解決此問題

		// 正解
		InterfaceA a = (ObjectA) oo;
		a.testName();

	}
}
