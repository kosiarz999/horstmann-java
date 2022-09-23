package pair3;

import java.util.Objects;

public class Manager extends Employee {

    private double bonus;

    public Manager(String name, double salary, int year, int month, int day) {
        super(name, salary, year, month, day);
        bonus = 0;
    }

    @Override
    public double getSalary() {
        return super.getSalary() + bonus;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        if (!super.equals(o)) return false;
//        var manager = (Manager) o;
//        return Double.compare(manager.bonus, bonus) == 0;
//    }
//
//    @Override
//    public String toString() {
//        return "Manager{" +
//                "bonus=" + bonus +
//                '}';
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(super.hashCode(), bonus);
//    }
}
