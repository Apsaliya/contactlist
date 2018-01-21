package com.contactlist.view.ui;

/**
 * Created by ankit on 21/01/18.
 */

public class States {
    public static int LOADING = 0;
    public static int LOADING_FINISHED = 1;
    public static int ERROR = 2;
    private int state;
    private String msg;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
