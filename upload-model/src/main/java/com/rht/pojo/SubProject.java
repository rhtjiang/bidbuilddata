package com.rht.pojo;

public class SubProject {
    //id
    private int id;
    //清单编码
    private String bop;
    //清单名称
    private String p_name;
    //清单描述
    private String p_detail;
    //计量单位
    private String prickle;
    //工程量
    private double amount;
   //综合单价
    private double i_unit;
    //暂估价
    private double evaluate;
    //分部名称
    private String partial_name;
    //项目名称
    private String b_name;

    public SubProject() {
        super();
    }

    public SubProject(int id, String bop, String p_name, String p_detail, String prickle, double amount, double i_unit, double evaluate, String partial_name, String b_name) {
        this.id = id;
        this.bop = bop;
        this.p_name = p_name;
        this.p_detail = p_detail;
        this.prickle = prickle;
        this.amount = amount;
        this.i_unit = i_unit;
        this.evaluate = evaluate;
        this.partial_name = partial_name;
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

    public String getP_detail() {
        return p_detail;
    }

    public void setP_detail(String p_detail) {
        this.p_detail = p_detail;
    }

    public String getPrickle() {
        return prickle;
    }

    public void setPrickle(String prickle) {
        this.prickle = prickle;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getI_unit() {
        return i_unit;
    }

    public void setI_unit(double i_unit) {
        this.i_unit = i_unit;
    }

    public double getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(double evaluate) {
        this.evaluate = evaluate;
    }

    public String getPartial_name() {
        return partial_name;
    }

    public void setPartial_name(String partial_name) {
        this.partial_name = partial_name;
    }

    public String getB_name() {
        return b_name;
    }

    public void setB_name(String b_name) {
        this.b_name = b_name;
    }

    @Override
    public String toString() {
        return "SubProject{" +
                "id=" + id +
                ", bop='" + bop + '\'' +
                ", p_name='" + p_name + '\'' +
                ", p_detail='" + p_detail + '\'' +
                ", prickle='" + prickle + '\'' +
                ", amount=" + amount +
                ", i_unit=" + i_unit +
                ", evaluate=" + evaluate +
                ", partial_name='" + partial_name + '\'' +
                ", b_name='" + b_name + '\'' +
                '}';
    }
}
