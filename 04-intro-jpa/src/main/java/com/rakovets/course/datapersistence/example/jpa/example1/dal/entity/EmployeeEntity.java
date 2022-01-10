package com.rakovets.course.datapersistence.example.jpa.example1.dal.entity;

import java.util.Objects;

public class EmployeeEntity {
    private int id;
    private String name;
    private double salary;
    private String position;

    public EmployeeEntity(String name, double salary, String position) {
        super();
        this.name = name;
        this.salary = salary;
        this.position = position;
    }

    public EmployeeEntity() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeEntity employeeEntity = (EmployeeEntity) o;
        return Double.compare(employeeEntity.salary, salary) == 0 &&
                Objects.equals(name, employeeEntity.name) &&
                Objects.equals(position, employeeEntity.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, salary, position);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", position='" + position + '\'' +
                '}';
    }
}
