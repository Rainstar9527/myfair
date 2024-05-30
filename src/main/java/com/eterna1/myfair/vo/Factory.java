package com.eterna1.myfair.vo;

public class Factory {
    private Integer facId;

    private String facName;

    private String facDesc;

    private String facAddress;

    private String facPhone;

    private String facLogo;

    private Integer facState;

    public Integer getFacId() {
        return facId;
    }

    public void setFacId(Integer facId) {
        this.facId = facId;
    }

    public String getFacName() {
        return facName;
    }

    public void setFacName(String facName) {
        this.facName = facName == null ? null : facName.trim();
    }

    public String getFacDesc() {
        return facDesc;
    }

    public void setFacDesc(String facDesc) {
        this.facDesc = facDesc == null ? null : facDesc.trim();
    }

    public String getFacAddress() {
        return facAddress;
    }

    public void setFacAddress(String facAddress) {
        this.facAddress = facAddress == null ? null : facAddress.trim();
    }

    public String getFacPhone() {
        return facPhone;
    }

    public void setFacPhone(String facPhone) {
        this.facPhone = facPhone == null ? null : facPhone.trim();
    }

    public String getFacLogo() {
        return facLogo;
    }

    public void setFacLogo(String facLogo) {
        this.facLogo = facLogo == null ? null : facLogo.trim();
    }

    public Integer getFacState() {
        return facState;
    }

    public void setFacState(Integer facState) {
        this.facState = facState;
    }
}