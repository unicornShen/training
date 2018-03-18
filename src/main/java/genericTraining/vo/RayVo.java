package genericTraining.vo;

import java.util.ArrayList;
import java.util.List;

public class RayVo {
    private String name;

    private int age;

    private List<String> work = new ArrayList<String>();

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getWork() {
        return this.work;
    }

    public void setWork(List<String> work) {
        this.work = work;
    }

}
