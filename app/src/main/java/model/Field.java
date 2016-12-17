package model;


import java.io.Serializable;

public class Field implements Serializable{
    private static final long serialVersionUID = 10L;

    private int fieldId;
    private String name;
    private String addr;
    private String tel;
    private String type;

    public Field(int fieldId, String name, String addr, String tel, String type) {
        this.fieldId = fieldId;
        this.name = name;
        this.addr = addr;
        this.tel = tel;
        this.type = type;
    }

    public Field() {
    }

    public static long getSerialVersionUID() {

        return serialVersionUID;
    }

    public int getFieldId() {
        return fieldId;
    }

    public void setFieldId(int fieldId) {
        this.fieldId = fieldId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



}
