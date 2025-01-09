package violet.neon.demostream.controller;

import org.springframework.web.bind.annotation.*;
import violet.neon.demostream.model.Employee;
import violet.neon.demostream.service.DepartmentService;

import java.util.Collection;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path = "/{id}/salary/sum")
    public int sumSalary(@PathVariable int id) {
        return departmentService.sumSalaryByDepartment(id);
    }

    @GetMapping(path = "/{id}/salary/max")
    public Employee maxSalary(@PathVariable int id) {
        return departmentService.findMaxSalaryByDepartment(id);
    }

    @GetMapping(path = "/{id}/salary/min")
    public Employee minSalary(@PathVariable int id) {
        return departmentService.findMinSalaryByDepartment(id);
    }

    @GetMapping(path = "/employees")
    public Collection all() {
        return departmentService.findAll().entrySet();
    }

    @GetMapping(path = "/{id}/employees")
    public Collection allEmployeesByDepartment(@PathVariable int id) {
        return departmentService.findAllByDepartment(id);
    }
}
