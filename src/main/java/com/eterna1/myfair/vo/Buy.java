package com.eterna1.myfair.vo;

public class Buy {
    private Integer buyId;

    private String buyName;

    private String buyDesc;

    private String buyLogo;

    private String buyAddress;

    private Integer buyState;

    public Integer getBuyId() {
        return buyId;
    }

    public void setBuyId(Integer buyId) {
        this.buyId = buyId;
    }

    public String getBuyName() {
        return buyName;
    }

    public void setBuyName(String buyName) {
        this.buyName = buyName == null ? null : buyName.trim();
    }

    public String getBuyDesc() {
        return buyDesc;
    }

    public void setBuyDesc(String buyDesc) {
        this.buyDesc = buyDesc == null ? null : buyDesc.trim();
    }

    public String getBuyLogo() {
        return buyLogo;
    }

    public void setBuyLogo(String buyLogo) {
        this.buyLogo = buyLogo == null ? null : buyLogo.trim();
    }

    public String getBuyAddress() {
        return buyAddress;
    }

    public void setBuyAddress(String buyAddress) {
        this.buyAddress = buyAddress == null ? null : buyAddress.trim();
    }

    public Integer getBuyState() {
        return buyState;
    }

    public void setBuyState(Integer buyState) {
        this.buyState = buyState;
    }
}