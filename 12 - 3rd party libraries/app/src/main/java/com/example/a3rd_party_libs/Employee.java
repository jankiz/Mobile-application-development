package com.example.a3rd_party_libs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee {
    private Integer employeeId;
    private String name;
    private String company;
    private String emailAddress;
/*
    public Employee() { }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public Employee(Integer employeeId, String name, String company, String emailAddress) {
        super();
        this.employeeId = employeeId;
        this.name = name;
        this.company = company;
        this.emailAddress = emailAddress;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }*/
}
