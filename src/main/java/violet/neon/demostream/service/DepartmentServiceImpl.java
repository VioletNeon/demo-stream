package violet.neon.demostream.service;

import org.springframework.stereotype.Service;
import violet.neon.demostream.model.Employee;

import java.util.Comparator;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee findMaxSalaryByDepartment(int departmentId) {
        List<Employee> employeeByDepartment = employeeService.getEmployeeListByDepartment(departmentId);

        return employeeByDepartment.stream().max(Comparator.comparingInt(Employee::getSalary)).get();
    }

    @Override
    public Employee findMinSalaryByDepartment(int departmentId) {
        List<Employee> employeeByDepartment = employeeService.getEmployeeListByDepartment(departmentId);

        return employeeByDepartment.stream().min(Comparator.comparingInt(Employee::getSalary)).get();
    }

    @Override
    public List<Employee> findAllByDepartment(int departmentId) {
        return employeeService.getEmployeeListByDepartment(departmentId);
    }

    @Override
    public List<Employee> findAll() {
        return employeeService.findAllEmployees().stream().toList();
    }
}
