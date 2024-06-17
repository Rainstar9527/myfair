package com.eterna1.myfair.vo;


public class RecordResult extends Record{
    private String mcode;
    private String mname;

    public RecordResult(){
        super();
        super.setMember(new Member());
    }

    public void setMcode(String mcode){
        this.mcode = mcode;
        super.getMember().setMcode(mcode);
    }

    public void setMname(String mname){
        this.mname = mname;
        super.getMember().setMname(mname);
    }
}
