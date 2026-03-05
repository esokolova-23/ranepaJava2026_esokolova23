package ru.ranepa.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Employee {
    private long id;
    private String name;
    private String position;
    private BigDecimal salary;
    private  final LocalDate hireDate;

    public Employee(String position, String name, double salary, LocalDate hireDate) {
        this.position = position;
        this.name = name;
        this.salary = BigDecimal.valueOf(salary);
        this.hireDate = hireDate;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                ", hireDate=" + hireDate +
                '}';
    }
}
