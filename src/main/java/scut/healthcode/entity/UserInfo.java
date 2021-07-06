package scut.healthcode.entity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserInfo {
    private String ID;
    private String name;
    private String residence;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }
}
