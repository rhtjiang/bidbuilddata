package com.rht.pojo;

public class Taxes {

    //id
    private int id;
    //项目名称
    private String p_name;
    //计算基础
    private String base;
    //计算基数
    private String base_number;
    //计算费率
    private String rate;
    //金额
    private String money;
    //工程名称
    private String b_name;


    public Taxes() {
        super();
    }

    public Taxes(int id, String p_name, String base, String base_number, String rate, String money, String b_name) {
        this.id = id;
        this.p_name = p_name;
        this.base = base;
        this.base_number = base_number;
        this.rate = rate;
        this.money = money;
        this.b_name = b_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getBase_number() {
        return base_number;
    }

    public void setBase_number(String base_number) {
        this.base_number = base_number;
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

    public String getB_name() {
        return b_name;
    }

    public void setB_name(String b_name) {
        this.b_name = b_name;
    }

    @Override
    public String toString() {
        return "Taxes{" +
                "id=" + id +
                ", p_name='" + p_name + '\'' +
                ", base='" + base + '\'' +
                ", base_number='" + base_number + '\'' +
                ", rate='" + rate + '\'' +
                ", money='" + money + '\'' +
                ", b_name='" + b_name + '\'' +
                '}';
    }
}
