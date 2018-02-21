package aopTraining.provider;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import aopTraining.implement.ServiceImp;


public class InstanceProvider {

    private static InstanceProvider instance;

    public static InstanceProvider getInstance() {
        ApplicationContext con = new ClassPathXmlApplicationContext("applicationContext.xml");
        instance = (InstanceProvider) con.getBean("instanceProvider");
        return instance;
    }

    //--------------------

    private ServiceImp serviceImp;

    public ServiceImp getServiceImp() {
        return this.serviceImp;
    }

    public void setServiceImp(ServiceImp serviceImp) {
        this.serviceImp = serviceImp;
    }

}
