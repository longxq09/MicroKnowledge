package com.wzs.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class UserInfo implements Serializable {

    private long id;
    private String email;
    private String name;
    private String sex;
    //private Date birth;
    private String address;
    private String signature;   //个性签名
    private String education;   //学历
    private String work;        //工作信息，包括affiliation即工作单位
    private ArrayList<String> expertise;    //专业领域
    private ArrayList<String> interest;    //偏好领域
    private String introduction;            //简介（选填）
    private String contribution;            //贡献（选填）

    public void setInfo(String email, String name, String sex,String address, String work){
        this.address=address;
        this.name=name;
        this.work=work;
        this.email=email;
        this.sex=sex;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public ArrayList<String> getExpertise() {
        return expertise;
    }

    public void setExpertise(ArrayList<String> expertise) {
        this.expertise = expertise;
    }

    public ArrayList<String> getInterest() {
        return interest;
    }

    public void setInterest(ArrayList<String> interest) {
        this.interest = interest;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getContribution() {
        return contribution;
    }

    public void setContribution(String contribution) {
        this.contribution = contribution;
    }
}
