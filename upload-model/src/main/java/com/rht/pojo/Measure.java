package com.rht.pojo;

public class Measure {
    //id
    private int id;
    //项目编码
    private String bop;
    //项目名称
    private String p_name;
    //计算基础
    private String base;
    //费率
    private String rate;
    //金额
    private String money;
    //调整费率
    private String adjust_rate;
    //调整后金额
    private String adjust_after_money;
    //备注
    private String comment;
    //工程名称
    private String b_name;


    public Measure() {
        super();
    }

    public Measure(int id, String bop, String p_name, String base, String rate, String money, String adjust_rate, String adjust_after_money, String comment, String b_name) {
        this.id = id;
        this.bop = bop;
        this.p_name = p_name;
        this.base = base;
        this.rate = rate;
        this.money = money;
        this.adjust_rate = adjust_rate;
        this.adjust_after_money = adjust_after_money;
        this.comment = comment;
        this.b_name = b_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBop() {
        return bop;
    }

    public void setBop(String bop) {
        this.bop = bop;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getAdjust_rate() {
        return adjust_rate;
    }

    public void setAdjust_rate(String adjust_rate) {
        this.adjust_rate = adjust_rate;
    }

    public String getAdjust_after_money() {
        return adjust_after_money;
    }

    public void setAdjust_after_money(String adjust_after_money) {
        this.adjust_after_money = adjust_after_money;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getB_name() {
        return b_name;
    }

    public void setB_name(String b_name) {
        this.b_name = b_name;
    }

    @Override
    public String toString() {
        return "Measure{" +
                "id=" + id +
                ", bop='" + bop + '\'' +
                ", p_name='" + p_name + '\'' +
                ", base='" + base + '\'' +
                ", rate='" + rate + '\'' +
                ", money='" + money + '\'' +
                ", adjust_rate='" + adjust_rate + '\'' +
                ", adjust_after_money='" + adjust_after_money + '\'' +
                ", comment='" + comment + '\'' +
                ", b_name='" + b_name + '\'' +
                '}';
    }
}
