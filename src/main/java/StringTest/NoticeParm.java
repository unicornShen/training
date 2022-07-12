package StringTest;

public class NoticeParm {
    /** 流程序號. */
    private Long todoItemSeq;

    /** 案件序號. */
    private Long caseSeq;

    /** 案件別. */
    private String caseType;

    /** 階段代碼. */
    private String stageId;

    /** 通知代碼 EX: NSE0001. */
    private String notifyCode;

    /** 是否已取件 T/F. */
    private String pickedUp;

    public Long getTodoItemSeq() {
        return todoItemSeq;
    }

    public void setTodoItemSeq(Long todoItemSeq) {
        this.todoItemSeq = todoItemSeq;
    }

    public Long getCaseSeq() {
        return caseSeq;
    }

    public void setCaseSeq(Long caseSeq) {
        this.caseSeq = caseSeq;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getNotifyCode() {
        return notifyCode;
    }

    public void setNotifyCode(String notifyCode) {
        this.notifyCode = notifyCode;
    }

    public String getPickedUp() {
        return pickedUp;
    }

    public void setPickedUp(String pickedUp) {
        this.pickedUp = pickedUp;
    }

}
