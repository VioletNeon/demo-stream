package violet.neon.demostream.service;

import violet.neon.demostream.model.Employee;

import java.util.List;

public interface DepartmentService {
    Employee findMaxSalaryByDepartment(int departmentId);

    Employee findMinSalaryByDepartment(int departmentId);

    List<Employee> findAllByDepartment(int departmentId);

    List<Employee> findAll();
}
