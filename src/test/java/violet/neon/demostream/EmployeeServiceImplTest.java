package violet.neon.demostream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import violet.neon.demostream.exception.EmployeeAlreadyAddedException;
import violet.neon.demostream.exception.EmployeeNotFoundException;
import violet.neon.demostream.model.Employee;
import violet.neon.demostream.service.EmployeeService;
import violet.neon.demostream.service.EmployeeServiceImpl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static violet.neon.demostream.EmployeeServiceImplTestConstants.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {
    private final EmployeeService out = new EmployeeServiceImpl();

    @Test
    public void shouldAddEmployeeAndReturnTheSame() {
        Employee result = out.addEmployee(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT);
        Employee expected = new Employee(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT);

        assertEquals(result, expected);
    }

    @Test
    public void shouldThrowEmployeeAlreadyAddedExceptionIfTheEmployeeAlreadyExists() {
        out.addEmployee(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT);

        assertThrows(EmployeeAlreadyAddedException.class, () -> {
            out.addEmployee(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT);
        });
    }

    @Test
    public void shouldFindEmployeeAndReturnOneIfItHasAlreadySaved() {
        Employee intermediateResult = out.addEmployee(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT);
        Employee result = out.findEmployee(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT);
        Employee expected = new Employee(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT);

        assertEquals(result, expected);
        assertEquals(intermediateResult, expected);
    }

    @Test
    public void shouldThrowEmployeeNotFoundExceptionIfEmployeeDoesNotExist() {
        assertThrows(EmployeeNotFoundException.class, () -> {
            out.findEmployee(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT);
        });
    }

    @Test
    public void shouldRemoveEmployeeAndReturnTheSameIfTheEmployeeSavedInCollection() {
        Employee expected = out.addEmployee(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT);
        Employee result = out.removeEmployee(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT);

        assertEquals(result, expected);

        assertThrows(EmployeeNotFoundException.class, () -> {
            out.findEmployee(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT);
        });
    }

    @Test
    public void shouldThrowEmployeeNotFoundExceptionIfEmployeeDoesNotExistToRemove() {
        assertThrows(EmployeeNotFoundException.class, () -> {
            out.removeEmployee(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT);
        });
    }

    @Test
    public void shouldReturnCollectionWithEmployees() {
        Employee employee1 = out.addEmployee(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT);
        Employee employee2 = out.addEmployee(LAST_NAME, FIRST_NAME, SALARY, DEPARTMENT);

        Collection<Employee> result = out.findAllEmployees();

        assertTrue(result.contains(employee1));
        assertTrue(result.contains(employee2));
        assertEquals(2, result.size());
    }

    @Test
    public void shouldReturnEmployeeListByPassedDepartment() {
        Employee employee1 = out.addEmployee(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT);
        Employee employee2 = out.addEmployee(LAST_NAME, FIRST_NAME, SALARY, DEPARTMENT);

        List<Employee> result = out.getEmployeeListByDepartment(DEPARTMENT);

        assertTrue(result.contains(employee1));
        assertTrue(result.contains(employee2));
        assertEquals(2, result.size());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionIfThereAreNotEmployeeFromPassedDepartment() {
        out.addEmployee(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT);
        out.addEmployee(LAST_NAME, FIRST_NAME, SALARY, DEPARTMENT);

        assertThrows(IllegalArgumentException.class, () -> {
            out.getEmployeeListByDepartment(4);
        });
    }

    @Test
    public void shouldReturnMinSalaryItemByDepartment() {
        int minSalaryMock = SALARY - 1;

        out.addEmployee(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT);
        out.addEmployee(LAST_NAME, FIRST_NAME, minSalaryMock, DEPARTMENT);

        int result = out.getSalaryMinByDepartment(DEPARTMENT);

        assertEquals(minSalaryMock, result);
    }

    @Test
    public void shouldReturnMaxSalaryItemByDepartment() {
        int maxSalaryMock = SALARY + 1;

        out.addEmployee(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT);
        out.addEmployee(LAST_NAME, FIRST_NAME, maxSalaryMock, DEPARTMENT);

        int result = out.getSalaryMaxByDepartment(DEPARTMENT);

        assertEquals(maxSalaryMock, result);
    }

    @Test
    public void shouldReturnSalaryAmountItemByDepartment() {
        int salaryMock = SALARY + 1;
        int expected = salaryMock + SALARY;

        out.addEmployee(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT);
        out.addEmployee(LAST_NAME, FIRST_NAME, salaryMock, DEPARTMENT);

        int result = out.getSalaryAmountByDepartment(DEPARTMENT);

        assertEquals(expected, result);
    }

    @Test
    public void shouldReturnSalaryAverageItemByDepartment() {
        int salaryMock = SALARY + 1;
        double expected = (double) (salaryMock + SALARY) / 2;

        out.addEmployee(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT);
        out.addEmployee(LAST_NAME, FIRST_NAME, salaryMock, DEPARTMENT);

        double result = out.getSalaryAverageByDepartment(DEPARTMENT);

        assertEquals(expected, result);
    }

    @Test
    public void shouldRewriteSalaryItemByIndexationPercentageInDepartmentForEveryEmployee() {
        int indexationPercentageMock = 5;
        Employee employee1 = out.addEmployee(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT);
        Employee employee2 = out.addEmployee(LAST_NAME, FIRST_NAME, SALARY, DEPARTMENT);

        Map<String, Integer> employeeMap = new HashMap<>();
        employeeMap.put(
                employee1.getFullName(),
                employee1.getSalary() + employee1.getSalary() * indexationPercentageMock / 100
        );
        employeeMap.put(
                employee2.getFullName(),
                employee2.getSalary() + employee2.getSalary() * indexationPercentageMock / 100
        );

        out.performSalaryIndexationByDepartment(indexationPercentageMock, DEPARTMENT);

        List<Employee> result = out.getEmployeeListByDepartment(DEPARTMENT);

        result.forEach((employee) -> {
            Integer expected = employeeMap.get(employee.getFullName());

            assertEquals(expected, employee.getSalary());
        });
    }
}
