package violet.neon.demostream.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import violet.neon.demostream.model.Employee;
import violet.neon.demostream.service.EmployeeService;

import java.util.Collection;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public Collection<Employee> findAll() {
        return employeeService.findAllEmployees();
    }

    @GetMapping(path = "/add")
    public Employee add(@RequestParam String firstName, @RequestParam String lastName, @RequestParam int salary, @RequestParam int department) {
        return employeeService.addEmployee(firstName, lastName, salary, department);
    }

    @GetMapping(path = "/find")
    public Employee find(@RequestParam String firstName, @RequestParam String lastName, @RequestParam int salary, @RequestParam int department) {
        return employeeService.findEmployee(firstName, lastName, salary, department);
    }

    @GetMapping(path = "/remove")
    public Employee remove(@RequestParam String firstName, @RequestParam String lastName, @RequestParam int salary, @RequestParam int department) {
        return employeeService.removeEmployee(firstName, lastName, salary, department);
    }
}