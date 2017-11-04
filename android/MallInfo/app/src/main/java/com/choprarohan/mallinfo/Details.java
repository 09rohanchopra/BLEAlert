package com.choprarohan.mallinfo;

/**
 * Created by Rohan Chopra on 11/4/17.
 */

public class Details {

    int perc;
    int sno;
    int type;

    public Details(int perc, int sno, int type) {
        this.perc = perc;
        this.sno = sno;
        this.type = type;
    }

    public int getPerc() {
        return perc;
    }

    public void setPerc(int perc) {
        this.perc = perc;
    }

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
