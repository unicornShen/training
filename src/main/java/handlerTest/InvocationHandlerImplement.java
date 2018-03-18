package handlerTest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class InvocationHandlerImplement implements InvocationHandler {

	private Object oo;
	
	public InvocationHandlerImplement(){
		super();
	}

	public InvocationHandlerImplement(Object oo) {
		this.oo = oo;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		beforDo(method.getName());
		
		Object result = method.invoke(this.oo, args);
		
		afterDo();
		
		return result;
	}
	
	public Object crown(Object oo){
		this.oo = oo;
		return Proxy.newProxyInstance(oo.getClass().getClassLoader(), oo.getClass().getInterfaces(), this);
	}

	private void beforDo(String methodName) {
		System.out.println("[U] before");
		BusinessLogic bl = new BusinessLogic();
		try {
//			bl.getClass().getMethod(methodName, null).invoke(bl, null);
			Method method = bl.getClass().getMethod(methodName, null);
			method.invoke(bl, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void afterDo() {
		System.out.println("[U] after");
		System.out.println("-------------------------");
	}
}
