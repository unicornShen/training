package queryMode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class St65FModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private String storeNo;
    private Date actDate;
    private String slipType;
    private Date inventoryDate;
    private String itemNo;
    private BigDecimal seq;
    private BigDecimal price;
    private BigDecimal storeCost;
    private BigDecimal saleCnt;
    private BigDecimal stockCnt;
    private String updFlag;
    private String updId;
    private Date updDate;

    private String storeName;

    public String getStoreNo() {
        return this.storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }

    public Date getActDate() {
        return this.actDate;
    }

    public void setActDate(Date actDate) {
        this.actDate = actDate;
    }

    public String getSlipType() {
        return this.slipType;
    }

    public void setSlipType(String slipType) {
        this.slipType = slipType;
    }

    public Date getInventoryDate() {
        return this.inventoryDate;
    }

    public void setInventoryDate(Date inventoryDate) {
        this.inventoryDate = inventoryDate;
    }

    public String getItemNo() {
        return this.itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public BigDecimal getSeq() {
        return this.seq;
    }

    public void setSeq(BigDecimal seq) {
        this.seq = seq;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getStoreCost() {
        return this.storeCost;
    }

    public void setStoreCost(BigDecimal storeCost) {
        this.storeCost = storeCost;
    }

    public BigDecimal getSaleCnt() {
        return this.saleCnt;
    }

    public void setSaleCnt(BigDecimal saleCnt) {
        this.saleCnt = saleCnt;
    }

    public BigDecimal getStockCnt() {
        return this.stockCnt;
    }

    public void setStockCnt(BigDecimal stockCnt) {
        this.stockCnt = stockCnt;
    }

    public String getUpdFlag() {
        return this.updFlag;
    }

    public void setUpdFlag(String updFlag) {
        this.updFlag = updFlag;
    }

    public String getUpdId() {
        return this.updId;
    }

    public void setUpdId(String updId) {
        this.updId = updId;
    }

    public Date getUpdDate() {
        return this.updDate;
    }

    public void setUpdDate(Date updDate) {
        this.updDate = updDate;
    }

    public String getStoreName() {
        return this.storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

}