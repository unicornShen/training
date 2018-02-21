package handlerTest;

import java.lang.reflect.Proxy;

public class MainTest extends InvocationHandlerImplement{

	public void main(String[] args) {
//		testOne();
		testTwo();
	}
	
	
	/**
	 * 第一種
	 */
	public static void testOne(){
		HandlerInterface hi = new HandlerImplement();

		InvocationHandlerImplement i = new InvocationHandlerImplement(hi);

		HandlerInterface face = (HandlerInterface) Proxy.newProxyInstance(
				hi.getClass().getClassLoader(),
				hi.getClass().getInterfaces(),
				i);

		face.testHandler();
	}
	
	/**
	 * 第二種	
	 */
	public void testTwo(){
//		InvocationHandlerImplement i = new InvocationHandlerImplement();
		
		HandlerInterface hi = (HandlerInterface) this.crown(new HandlerImplement());
		
		hi.testHandler();
		hi.testMultiMethod();
	}

}
