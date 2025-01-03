package violet.neon.demostream.model;

import java.util.Objects;

public class Employee {
    private final String firstName;
    private final String lastName;
    private int salary;
    private int department;

    public Employee(String firstName, String lastName, int salary, int department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.department = department;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    public int getSalary() {
        return this.salary;
    }

    public int getDepartment() {
        return this.department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee {" +
                "firstName=" + firstName +
                ", lastName='" + lastName +
                ", salary='" + salary +
                ", department='" + department +
                '}';
    }

    @Override
    public boolean equals(Object entity) {
        if (this == entity) return true;
        if (entity == null || getClass() != entity.getClass()) return false;

        Employee employee = (Employee) entity;

        return this.firstName.equals(employee.firstName) && this.lastName.equals(employee.lastName) && this.salary == employee.salary && this.department == employee.department;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.firstName, this.lastName, this.salary, this.department);
    }
}