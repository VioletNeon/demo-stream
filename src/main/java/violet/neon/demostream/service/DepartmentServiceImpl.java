package violet.neon.demostream.service;

import org.springframework.stereotype.Service;
import violet.neon.demostream.model.Employee;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee findMaxSalaryByDepartment(int departmentId) {
        return employeeService.getEmployeeListByDepartment(departmentId).stream().max(Comparator.comparingInt(Employee::getSalary)).get();
    }

    @Override
    public Employee findMinSalaryByDepartment(int departmentId) {
        return employeeService.getEmployeeListByDepartment(departmentId).stream().min(Comparator.comparingInt(Employee::getSalary)).get();
    }

    @Override
    public List<Employee> findAllByDepartment(int departmentId) {
        return employeeService.getEmployeeListByDepartment(departmentId);
    }

    @Override
    public Map<Integer, List<Employee>> findAll() {
        return employeeService.findAllEmployees().stream().collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
