package convert;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 */
public class ConvertModel {
    private String no;
    private int count;
    private Integer beforeQty;
    private long updId;
    private Long id;
    private BigDecimal afterQty;
    private Date updDate;

    public String getNo() {
        return this.no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Integer getBeforeQty() {
        return this.beforeQty;
    }

    public void setBeforeQty(Integer beforeQty) {
        this.beforeQty = beforeQty;
    }

    public long getUpdId() {
        return this.updId;
    }

    public void setUpdId(long updId) {
        this.updId = updId;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAfterQty() {
        return this.afterQty;
    }

    public void setAfterQty(BigDecimal afterQty) {
        this.afterQty = afterQty;
    }

    public Date getUpdDate() {
        return this.updDate;
    }

    public void setUpdDate(Date updDate) {
        this.updDate = updDate;
    }

}
