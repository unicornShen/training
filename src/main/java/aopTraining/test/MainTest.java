package aopTraining.test;

import aopTraining.instance.IService;
import aopTraining.provider.InstanceProvider;

public class MainTest {

    public static void main(String[] args) {
        System.out.println("[U]main");

        IService service = InstanceProvider.getInstance().getServiceImp();
        service.check();

    }
}
