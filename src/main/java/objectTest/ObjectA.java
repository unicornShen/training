package objectTest;

public class ObjectA implements InterfaceA {
	public int number;
	public String string;

	public ObjectA(){
		super();
	}
	
	public ObjectA(int number, String string) {
		this.number = number;
		this.string = string;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	public void testName() {
		System.out.println("number + string = [" + number + "]" + string);

	}

}
