package treeNode;

import java.io.Serializable;


/**
 * <pre>
 * 查核項目問題設定檔 VO物件.
 * </pre>
 *
 * @since 2017年6月14日
 * @author Unicorn
 * @version <ul>
 *            <li>2017年6月14日,Unicorn,new
 *          </ul>
 */
public class CheckItmeProblemVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 查核年. */
    private String year;

    /** 查核月. */
    private String month;

    /** 序號. */
    private String checkSeq;

    /** 父序號. */
    private String parent;

    /** 一般或特定風險態樣   B:一般 S:特定. */
    private String checkFlag;

    /** 排序. */
    private String checkOrder;

    /** 建立者. */
    private String creator;

    /** 建立時間. */
    private String createtime;

    /** 更新者ID. */
    private String modifier;

    /** 更新者名稱. */
    private String modifierName;

    /** 更新時間. */
    private String lastupdate;

    //----------------------------
    //---- Report field.
    //----------------------------

    /** 查核項目. */
    private String checkItem;

    /** 查核方式. */
    private String checkType;

    /** 查核問題. */
    private String checkProblem;

    //----------------------------
    //---- Other field.
    //----------------------------

    /** 查核回覆. */
    private String answer;

    /** 查核日期. */
    private String checkDate;

    /** 來源 Table. */
    private String srcTable;

    /** 來源 SEQ. */
    private String srcSeq;
    
    /** 狀態. */
    private String status;

    //----------------------------
    //---- get, set.
    //----------------------------

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getCheckSeq() {
        return checkSeq;
    }

    public void setCheckSeq(String checkSeq) {
        this.checkSeq = checkSeq;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(String checkFlag) {
        this.checkFlag = checkFlag;
    }

    public String getCheckOrder() {
        return checkOrder;
    }

    public void setCheckOrder(String checkOrder) {
        this.checkOrder = checkOrder;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getModifierName() {
        return modifierName;
    }

    public void setModifierName(String modifierName) {
        this.modifierName = modifierName;
    }

    public String getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(String lastupdate) {
        this.lastupdate = lastupdate;
    }

    public String getCheckItem() {
        return checkItem;
    }

    public void setCheckItem(String checkItem) {
        this.checkItem = checkItem;
    }

    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }

    public String getCheckProblem() {
        return checkProblem;
    }

    public void setCheckProblem(String checkProblem) {
        this.checkProblem = checkProblem;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public String getSrcTable() {
        return srcTable;
    }

    public void setSrcTable(String srcTable) {
        this.srcTable = srcTable;
    }

    public String getSrcSeq() {
        return srcSeq;
    }

    public void setSrcSeq(String srcSeq) {
        this.srcSeq = srcSeq;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
