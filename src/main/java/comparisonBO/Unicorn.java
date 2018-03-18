package comparisonBO;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class Unicorn {
	private String name;
	private Long empNo;
	private int age;
	private BigDecimal money;
	private Set<String> mitac;
	
	public Unicorn(){
		this.name = "劉";
		this.empNo = Long.valueOf("228");
		this.age = 18;
		this.money = new BigDecimal(7788);
		this.mitac = new HashSet<String>();
		this.mitac.add("a");
		this.mitac.add("b");
		this.mitac.add("c");
        this.mitac.add("d");
	}
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getEmpNo() {
		return this.empNo;
	}
	public void setEmpNo(Long empNo) {
		this.empNo = empNo;
	}
	public int getAge() {
		return this.age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public BigDecimal getMoney() {
		return this.money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Set<String> getMitac() {
		return this.mitac;
	}

	public void setMitac(Set<String> mitac) {
		this.mitac = mitac;
	}

}
