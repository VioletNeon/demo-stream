package violet.neon.demostream.service;

import violet.neon.demostream.model.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    int sumSalaryByDepartment(int departmentId);

    Employee findMaxSalaryByDepartment(int departmentId);

    Employee findMinSalaryByDepartment(int departmentId);

    List<Employee> findAllByDepartment(int departmentId);

    Map<Integer, List<Employee>> findAll();
}
