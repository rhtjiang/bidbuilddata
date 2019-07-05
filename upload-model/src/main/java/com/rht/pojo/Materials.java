package com.rht.pojo;

public class Materials {

    //id
    private int id;
    //材料名称
    private String m_name;
    //单位
    private String prickle;
    //数量
    private String amount;
    //风险系数
    private String risk_facto;
    //基准单价
    private String base_unit;
    //投标单价
    private String tender_unit;
    //发承包人确认单价
    private String ack_unit;
    //备注
    private String comment;


    public Materials() {
        super();
    }

    public Materials(int id, String m_name, String prickle, String amount, String risk_facto, String base_unit, String tender_unit, String ack_unit, String comment) {
        this.id = id;
        this.m_name = m_name;
        this.prickle = prickle;
        this.amount = amount;
        this.risk_facto = risk_facto;
        this.base_unit = base_unit;
        this.tender_unit = tender_unit;
        this.ack_unit = ack_unit;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getM_name() {
        return m_name;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }

    public String getPrickle() {
        return prickle;
    }

    public void setPrickle(String prickle) {
        this.prickle = prickle;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getRisk_facto() {
        return risk_facto;
    }

    public void setRisk_facto(String risk_facto) {
        this.risk_facto = risk_facto;
    }

    public String getBase_unit() {
        return base_unit;
    }

    public void setBase_unit(String base_unit) {
        this.base_unit = base_unit;
    }

    public String getTender_unit() {
        return tender_unit;
    }

    public void setTender_unit(String tender_unit) {
        this.tender_unit = tender_unit;
    }

    public String getAck_unit() {
        return ack_unit;
    }

    public void setAck_unit(String ack_unit) {
        this.ack_unit = ack_unit;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Materials{" +
                "id=" + id +
                ", m_name='" + m_name + '\'' +
                ", prickle='" + prickle + '\'' +
                ", amount='" + amount + '\'' +
                ", risk_facto='" + risk_facto + '\'' +
                ", base_unit='" + base_unit + '\'' +
                ", tender_unit='" + tender_unit + '\'' +
                ", ack_unit='" + ack_unit + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
