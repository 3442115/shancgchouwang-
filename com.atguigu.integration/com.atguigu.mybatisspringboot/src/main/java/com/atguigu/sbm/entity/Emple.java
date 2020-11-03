package com.atguigu.sbm.entity;

public class Emple {
    private Integer empId;

    private String empName;

    private Integer empAge;

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName == null ? null : empName.trim();
    }

    public Integer getEmpAge() {
        return empAge;
    }

    public void setEmpAge(Integer empAge) {
        this.empAge = empAge;
    }

    public Emple(Integer empId, String empName, Integer empAge) {
        this.empId = empId;
        this.empName = empName;
        this.empAge = empAge;
    }

    public Emple() {
    }

    @Override
    public String toString() {
        return "Emple{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", empAge=" + empAge +
                '}';
    }
}