package handlerTest.annotationUse;

public class ControllerImpl extends BaseController {
    
    
    @Override
    public void doSave() {
        System.out.println("doSave");
    }

    @Override
    public void doUpdate() {
        System.out.println("doUpdate");
    }

    @Override
    public void doDelete() {
        System.out.println("doDelete");
    }
}
