package interfaceTest;

public class BusinessObject {
	private IDeviceWriter writer; 

    public void setDeviceWriter(IDeviceWriter writer) { 
        this.writer = writer; 
    } 

    public void save() { 
        writer.saveToDevice(); 
    } 

}
