package objectCoyp;

import java.lang.reflect.Method;

import org.apache.commons.beanutils.PropertyUtils;

public class TurnTool<T, S> {

	public T turnToTarget(T target, S source) {
		try {
			PropertyUtils.copyProperties(target, source);

			findColection(source);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return target;
	}

	private void findColection(S source) {
		Method[] methods = source.getClass().getDeclaredMethods();
		System.out.println("source.getClass().getName() = " + source.getClass().getName());
		for (Method method : methods) {
			System.out.println("method.getName() = " + method.getName());
			System.out.println("method.getParameterTypes() = " + method.getParameterTypes());
			System.out.println("method.getReturnType() = " + method.getReturnType());
		}
	}

}
