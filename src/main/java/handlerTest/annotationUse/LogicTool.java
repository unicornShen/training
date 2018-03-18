package handlerTest.annotationUse;

/**
 * 檢核工具xxx
 * 模擬Controller
 * Current not finished, if have time, back try.
 */
public class LogicTool implements ICheck{
    //============
    //==
    InvocationHandlerImpl imp = new InvocationHandlerImpl();

    private ICheck check;

    LogicTool() {
        super();
//        this.check = (ICheck) this.imp.generateProxy(new CheckImpl());
    }
    
    LogicTool(ICheck check) {
//        this.check = (ICheck) this.imp.generateProxy(check);
    }

    public ICheck getCheck() {
        return this.check;
    }

    public void setCheck(ICheck check) {
        this.check = check;
    }
    //== 
    //============
    
    public void saveCheck() {
        this.check.saveCheck();
    }

    public void updateCheck() {
        this.check.updateCheck();
    }

    public void deleteCheck() {
//        this.check.deleteCheck();
        this.testThisMethod();
    }

    
    public void otherTest(){
        this.check = (ICheck) this.imp.generateProxy(this);
        
        this.check.deleteCheck();
    }
    
    public void testThisMethod(){
        System.out.println("YA!");
    }
    
}
