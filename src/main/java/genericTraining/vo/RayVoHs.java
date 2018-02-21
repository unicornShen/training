package genericTraining.vo;

import genericTraining.phase2.IExample;

import java.util.ArrayList;
import java.util.List;

/**
 * 在implements IExample 中指定轉換的型別。
 *
 * @author Unicorn
 */
public class RayVoHs implements IExample<RayVoHs, RayVo> {

    /**
     * 自訂邏輯處理。
     */
    public RayVoHs convert(RayVo source) {
        // 以下可以使用任意邏輯做處理。
        RayVoHs hs = new RayVoHs();
        hs.setProcessId(source.getName() + source.getAge());

        return hs;
    }

    // ------以下不是重點--------

    private String processId;

    private String name;

    private int age;

    private List<String> work = new ArrayList<String>();

    public String getProcessId() {
        return this.processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

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
