package model;


import java.io.Serializable;

public class Plan implements Serializable{
    private static final long serialVersionUID = 10L;

    private int planId;
    private String fieldName;
    private String time;
    private String date;

    public Plan(int planId, String fieldName, String time, String date) {
        this.planId = planId;
        this.fieldName = fieldName;
        this.time = time;
        this.date = date;
    }

    public Plan() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
