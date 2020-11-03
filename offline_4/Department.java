public class Department {

    // add your code here
    // there can be at most 20 departments
    private int id;
    private String name;
    private Employee[] employees;
    private int employeeCount;
    private static Department[] departments;
    private static int departmentCount;
    static {
        departments = new Department[20];
        departmentCount = 0;
    }

    // you are not allowed to write any other constructor
    public Department(int id, String name) {
        // add your code here
        this.id = id;
        this.name = name;
        this.employees = new Employee[10];
        this.employeeCount = 0;
        departments[departmentCount] = this;
        departmentCount++;
    }

    public void addEmployee(Employee employee) {
        this.employees[employeeCount] = employee;
        employeeCount++;
    }

    public double getDepartmentSalary() {
        double totalSalary = 0;
        for (int i = 0; i < employeeCount; i++) {
            totalSalary += this.employees[i].getSalary();
        }
        return totalSalary;
    }

    public Employee getMaxSalaryEmployee() {
        double salary = this.employees[0].getSalary();
        int numberofEmployee = 0;
        for (int i = 0; i < employeeCount; i++) {
            if (salary < this.employees[i].getSalary()) {
                numberofEmployee = i;
                salary = this.employees[i].getSalary();
            }
        }
        return this.employees[numberofEmployee];
    }

    public static double getTotalSalary() {
        double totalSalary = 0;
        for (int i = 0; i < 20; i++) {
            if (departments[i] != null) {
                totalSalary += departments[i].getDepartmentSalary();
            }
        }
        return totalSalary;
    }

    // add your code here
}