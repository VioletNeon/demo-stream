package violet.neon.demostream.service;

import org.springframework.stereotype.Service;
import violet.neon.demostream.model.Employee;
import violet.neon.demostream.exception.EmployeeAlreadyAddedException;
import violet.neon.demostream.exception.EmployeeNotFoundException;
import violet.neon.demostream.exception.EmployeeStorageIsFullException;

import java.util.*;
import java.util.stream.IntStream;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String, Employee> employees;
    private final int MAX_EMPLOYEES = 10;

    public EmployeeServiceImpl() {
        this.employees = new HashMap<>();
    }

    @Override
    public Employee addEmployee(String firstName, String lastName, int salary, int department) {
        if (employees.size() == MAX_EMPLOYEES) {
            throw new EmployeeStorageIsFullException();
        }

        Employee employee = new Employee(firstName, lastName, salary, department);

        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException();
        }

        employees.put(employee.getFullName(), employee);

        return employee;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName, int salary, int department) {
        Employee employee = new Employee(firstName, lastName, salary, department);

        if (!employees.containsKey(employee.getFullName())) {
            throw new EmployeeNotFoundException();
        }

        return employee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName, int salary, int department) {
        Employee employee = new Employee(firstName, lastName, salary, department);

        if (!employees.containsKey(employee.getFullName())) {
            throw new EmployeeNotFoundException();
        }

        employees.remove(employee.getFullName());

        return employee;
    }

    @Override
    public Collection<Employee> findAllEmployees() {
        return Collections.unmodifiableCollection(employees.values());
    }

    @Override
    public List<Employee> getEmployeeListByDepartment(int department) {
        List<Employee> employeesByDepartment = findAllEmployees()
                .stream()
                .filter(e -> e.getDepartment() == department)
                .toList();

        if (employeesByDepartment.isEmpty()) {
            throw new IllegalArgumentException("В департамент " + department + " сотрудники не наняты");
        }

        return employeesByDepartment;
    }

    @Override
    public int getSalaryMinByDepartment(int department) {
        Employee employeeWithMinSalary = getEmployeeListByDepartment(department)
                .stream()
                .min(Comparator.comparingInt(Employee::getSalary))
                .get();

        return employeeWithMinSalary.getSalary();
    }

    @Override
    public int getSalaryMaxByDepartment(int department) {
        Employee employeeWithMaxSalary = getEmployeeListByDepartment(department)
                .stream()
                .max(Comparator.comparingInt(Employee::getSalary))
                .get();

        return employeeWithMaxSalary.getSalary();
    }

    @Override
    public int getSalaryAmountByDepartment(int department) {
        return getEmployeeListByDepartment(department)
                .stream()
                .flatMapToInt(e -> IntStream.of(e.getSalary()))
                .sum();
    }

    @Override
    public double getSalaryAverageByDepartment(int department) {
        return getEmployeeListByDepartment(department)
                .stream()
                .flatMapToInt(e -> IntStream.of(e.getSalary()))
                .average()
                .getAsDouble();
    }

    @Override
    public void performSalaryIndexationByDepartment(int indexationPercentage, int department) {
        getEmployeeListByDepartment(department)
                .forEach(e -> {
                    int salary = e.getSalary();
                    salary += salary * indexationPercentage / 100;

                    e.setSalary(salary);
                });
    }

    private void showEmployeeWithoutDepartment(Employee employee) {
        System.out.println("Employee {" + "fullName='" + employee.getFullName() + '\'' + ", salary=" + employee.getSalary() + '}');
    }

    @Override
    public void showEmployeesByDepartment(int department) {
        List<Employee> employeeByDepartment = getEmployeeListByDepartment(department);

        employeeByDepartment.forEach(this::showEmployeeWithoutDepartment);
    }
}