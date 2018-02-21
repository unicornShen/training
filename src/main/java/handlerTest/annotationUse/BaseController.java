package handlerTest.annotationUse;

public abstract class BaseController implements IController {
    InvocationHandlerImpl imp = new InvocationHandlerImpl();
    IController controller;

    public abstract void doSave();

    public abstract void doUpdate();

    public abstract void doDelete();
    
    /**
     * 要怎麼註冊，目前還沒想法 
     */
    public IController setProxyController(IController ctl){
        this.controller = (IController) this.imp.generateProxy(ctl);
        
        return this.controller;
    }
    
    
    
}
