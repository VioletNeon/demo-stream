package violet.neon.demostream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import violet.neon.demostream.model.Employee;
import violet.neon.demostream.service.DepartmentServiceImpl;
import violet.neon.demostream.service.EmployeeService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static violet.neon.demostream.DepartmentServiceImplTestConstants.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {
    @Mock
    private EmployeeService employeeServiceMock;

    @InjectMocks
    private DepartmentServiceImpl out;

    @Test
    public void shouldReturnSalaryAmountByDepartment() {
        int expected = employeeMock1.getSalary() + employeeMock2.getSalary();

        when(employeeServiceMock.getEmployeeListByDepartment(DEPARTMENT_FIRST)).thenReturn(employeeListMock1);

        int result = out.sumSalaryByDepartment(DEPARTMENT_FIRST);

        assertEquals(result, expected);

        verify(employeeServiceMock, times(1)).getEmployeeListByDepartment(DEPARTMENT_FIRST);
    }

    @Test
    public void shouldReturnEmployeeEntityWithMaxSalaryByDepartment() {
        when(employeeServiceMock.getEmployeeListByDepartment(DEPARTMENT_FIRST)).thenReturn(employeeListMock1);

        Employee result = out.findMaxSalaryByDepartment(DEPARTMENT_FIRST);

        assertEquals(result, employeeMock2);

        verify(employeeServiceMock, times(1)).getEmployeeListByDepartment(DEPARTMENT_FIRST);
    }

    @Test
    public void shouldReturnEmployeeEntityWithMinSalaryByDepartment() {
        when(employeeServiceMock.getEmployeeListByDepartment(DEPARTMENT_FIRST)).thenReturn(employeeListMock1);

        Employee result = out.findMinSalaryByDepartment(DEPARTMENT_FIRST);

        assertEquals(result, employeeMock1);

        verify(employeeServiceMock, times(1)).getEmployeeListByDepartment(DEPARTMENT_FIRST);
    }

    @Test
    public void shouldReturnEmployeeListEntityByDepartment() {
        when(employeeServiceMock.getEmployeeListByDepartment(DEPARTMENT_SECOND)).thenReturn(employeeListMock2);

        List<Employee> result = out.findAllByDepartment(DEPARTMENT_SECOND);

        assertEquals(result, employeeListMock2);

        verify(employeeServiceMock, times(1)).getEmployeeListByDepartment(DEPARTMENT_SECOND);
    }

    @Test
    public void shouldReturnEmployeeMapWhereDepartmentOfItemIsKey() {
        Map<Integer, List<Employee>> allEmployeeMapMock = new HashMap<>();
        when(employeeServiceMock.findAllEmployees()).thenReturn(List.of(employeeMock1, employeeMock4));

        allEmployeeMapMock.put(employeeMock1.getDepartment(), List.of(employeeMock1));
        allEmployeeMapMock.put(employeeMock4.getDepartment(), List.of(employeeMock4));

        Map<Integer, List<Employee>> result = out.findAll();

        assertEquals(result, allEmployeeMapMock);

        verify(employeeServiceMock, times(1)).findAllEmployees();
    }
}
