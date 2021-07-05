package scut.healthcode.entity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NucleicAcidInfo {
    public String getID() {
        return ID;
    }

    public String getUserName() {
        return userName;
    }

    public String getTime() {
        return time;
    }

    public int getResult() {
        return result;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setResult(int result) {
        this.result = result;
    }

    private String ID;
    private String userName;
    private String time;
    private int result;



}