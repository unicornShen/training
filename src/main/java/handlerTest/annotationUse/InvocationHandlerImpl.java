package handlerTest.annotationUse;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class InvocationHandlerImpl implements InvocationHandler {

    private Object declar;

    public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
        System.out.println("aop");
        System.out.println("m.getName=" + m.getName());

        return m.invoke(this.declar, args);
    }

    public Object generateProxy(Object declar) {
        this.declar = declar;
        return Proxy.newProxyInstance(this.declar.getClass().getClassLoader(), this.declar.getClass().getInterfaces(), this);
    }

}
