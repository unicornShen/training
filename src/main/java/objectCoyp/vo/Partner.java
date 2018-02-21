package objectCoyp.vo;

import java.util.HashSet;
import java.util.Set;

public class Partner {
	private Long id;
	private String name;
	private int age;
	private Set<String> work = new HashSet<String>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Set<String> getWork() {
		return work;
	}

	public void setWork(Set<String> work) {
		this.work = work;
	}


}
