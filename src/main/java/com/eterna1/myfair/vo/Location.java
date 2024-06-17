package com.eterna1.myfair.vo;

public class Location {
    private Integer pubId;

    private String pubCode;

    private String pubLocation;

    private String pubDesc;

    private Integer facId;

    public Integer getPubId() {
        return pubId;
    }

    public void setPubId(Integer pubId) {
        this.pubId = pubId;
    }

    public String getPubCode() {
        return pubCode;
    }

    public void setPubCode(String pubCode) {
        this.pubCode = pubCode == null ? null : pubCode.trim();
    }

    public String getPubLocation() {
        return pubLocation;
    }

    public void setPubLocation(String pubLocation) {
        this.pubLocation = pubLocation == null ? null : pubLocation.trim();
    }

    public String getPubDesc() {
        return pubDesc;
    }

    public void setPubDesc(String pubDesc) {
        this.pubDesc = pubDesc == null ? null : pubDesc.trim();
    }

    public Integer getFacId() {
        return facId;
    }

    public void setFacId(Integer facId) {
        this.facId = facId;
    }
}