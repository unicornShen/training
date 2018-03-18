package interfaceTest;

public class TestMain {
	public static void main(String[] args){
		BusinessObject bo = new BusinessObject();
		bo.setDeviceWriter(new UsbDiskWriter());
		bo.save();
	}
}
