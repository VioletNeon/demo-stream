package violet.neon.demostream.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import violet.neon.demostream.model.Employee;
import violet.neon.demostream.service.DepartmentService;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path = "/max-salary")
    public Employee maxSalary(@RequestParam int departmentId) {
        return departmentService.findMaxSalaryByDepartment(departmentId);
    }

    @GetMapping(path = "/min-salary")
    public Employee minSalary(@RequestParam int departmentId) {
        return departmentService.findMinSalaryByDepartment(departmentId);
    }

    @GetMapping(path = "/all")
    public Collection all(@RequestParam(required = false) Optional<Integer> departmentId) {
        if (departmentId.isPresent()) {
            return departmentService.findAllByDepartment(departmentId.get());
        } else {
            return departmentService.findAll().entrySet();
        }
    }
}
