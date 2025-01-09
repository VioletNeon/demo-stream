package violet.neon.demostream;

import violet.neon.demostream.model.Employee;

import java.util.List;

public class DepartmentServiceImplTestConstants {
    public static final int DEPARTMENT_FIRST = 1;
    public static final int DEPARTMENT_SECOND = 2;
    public static final Employee employeeMock1 = new Employee("Richardson", "Chris", 30_000, DEPARTMENT_FIRST);
    public static final Employee employeeMock2 = new Employee("McConnell", "Steve", 33_000, DEPARTMENT_FIRST);
    public static final Employee employeeMock3 = new Employee("Evans", "Eric", 31_000, DEPARTMENT_SECOND);
    public static final Employee employeeMock4 = new Employee("Horstmann", "Cay", 35_000, DEPARTMENT_SECOND);
    public static final List<Employee> employeeListMock1 = List.of(
            employeeMock1,
            employeeMock2
    );
    public static final List<Employee> employeeListMock2 = List.of(
            employeeMock3,
            employeeMock4
    );
}