package violet.neon.demostream.service;

import violet.neon.demostream.model.Employee;

import java.util.Collection;
import java.util.List;

public interface EmployeeService {
    Employee addEmployee(String firstName, String lastName, int salary, int department);

    Employee findEmployee(String firstName, String lastName, int salary, int department);

    Employee removeEmployee(String firstName, String lastName, int salary, int department);

    Collection<Employee> findAllEmployees();

    List<Employee> getEmployeeListByDepartment(int department);

    float getSalaryMinByDepartment(int department);

    int getSalaryMaxByDepartment(int department);

    int getSalaryAmountByDepartment(int department);

    double getSalaryAverageByDepartment(int department);

    void performSalaryIndexationByDepartment(int indexationPercentage, int department);

    void showEmployeesByDepartment(int department);
}