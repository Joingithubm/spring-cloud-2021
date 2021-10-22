package com.athome.spring5.entity;

/**
 * @Description:
 * @Author Zengfc
 * @Date 2021/10/22 16:09
 * @Version 1.0
 */
public class Payment {
    private int id;
    private String serial;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", serial='" + serial + '\'' +
                '}';
    }
}
