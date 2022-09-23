package pair3;

import java.time.LocalDate;
import java.util.Objects;

public class Employee /*implements Comparable<Employee> */{
    private String name;
    private double salary;
    private LocalDate hireDay;

    public Employee(String name, double salary, int year, int month, int day) {
        this.name = name;
        this.salary = salary;
        this.hireDay = LocalDate.of(year, month, day);
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public LocalDate getHireDay() {
        return hireDay;
    }

    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100;
        salary += raise;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        var employee = (Employee) o;
//        return Double.compare(employee.salary, salary) == 0 && Objects.equals(name, employee.name) && Objects.equals(hireDay, employee.hireDay);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(name, salary, hireDay);
//    }
//
//    @Override
//    public String toString() {
//        return getClass().getName() +
//                "{name='" + name + '\'' +
//                ", salary=" + salary +
//                ", hireDay=" + hireDay +
//                '}';
//    }
//
//    @Override
//    public int compareTo(Employee o) {
//        return Double.compare(salary, o.salary);
//    }
}
