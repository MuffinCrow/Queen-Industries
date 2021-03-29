import javax.print.DocFlavor;
import java.util.Comparator;

public class Employee implements Comparable{
    private int salary;
    private String name;
    private String department;
    private String title;
    private int tier = 1;

    public Employee (int salary, String name, String department, String title) {
        this.salary = salary;
        this.name = name;
        this.department = department;
        this.title = title;
    }

    public Employee (int salary, String name, String department, String title, int tier) {
        this.salary = salary;
        this.name = name;
        this.department = department;
        this.title = title;
        this.tier = tier;
    }

    public String getName() {
        return this.name;
    }

    public String getDepartment() {
        return this.department;
    }

    public String getTitle() {
        return this.title;
    }

    public int getSalary() {
        return this.salary;
    }

    public int getTier() { return this.tier; }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setTier(int tier) { this.tier = tier; }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
