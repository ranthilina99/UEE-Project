package com.example.ueeversion1.Model;

public class SubCategory {
    private String pid;
    private String receptions;

    public SubCategory(String s) {

    }

    public SubCategory(String pid, String receptions) {
        this.pid = pid;
        this.receptions = receptions;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getReceptions() {
        return receptions;
    }

    public void setReceptions(String receptions) {
        this.receptions = receptions;
    }
}
