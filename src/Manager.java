import java.util.ArrayList;

public class Manager extends Employee {
    ArrayList<Employee> reports;
    int bonus;

    public Manager(int salary, int bonus, String name, String department, String title, ArrayList<Employee> reports, int tier) throws Exception {
        super(salary, name, department, title, tier);
        this.reports = reports;
        if(checkReports()) {
            this.bonus = bonus;
        }
    }

    private boolean checkReports() throws Exception {
        if (this.reports != null) {
            for (Employee e : this.reports) {
                if (this.getTier() <= e.getTier()) {
                    this.reports = null;
                    this.setName(null);
                    this.setDepartment(null);
                    this.setTitle(null);
                    this.setBonus(0);
                    this.setSalary(0);
                    throw new Exception("ERROR: cannot supervise an Employee of an equal or greater tier.");
                }
            }
        }
        return true;
    }

    public int getBonus() {
        return bonus;
    }

    public ArrayList<Employee> getReports() {
        return reports;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public void setReports(ArrayList<Employee> reports) {
        this.reports = reports;
    }

    public void addReports(Employee e) {
        reports.add(e);
    }

    public void removeReports(Employee e) {
        reports.remove(e);
    }

    public void adjustSalary(int i, Manager m) throws Exception {
       if (this.getTier() > m.getTier()) {
           if (this.getDepartment().equals(m.getDepartment())) {
               m.setSalary(m.getSalary() + i);
           } else {
               throw new Exception("ERROR: cannot alter salary of an Employee who is not a report.");
           }
       } else {
           throw new Exception("ERROR: cannot alter salary of an Employee of an equal or greater tier.");
       }
    }

    public void adjustSalary(int i, Employee e) throws Exception {
        if (this.getTier() > e.getTier()) {
            if (this.getDepartment().equals(e.getDepartment())) {
                e.setSalary(e.getSalary() + i);
            } else {
                throw new Exception("ERROR: cannot alter salary of an Employee who is not a report.");
            }
        } else {
            throw new Exception("ERROR: cannot alter salary of an Employee of an equal or greater tier.");
        }
    }

    public void hire(Manager manager) throws Exception {
        if (this.getTier() > manager.getTier()) {
            if (this.getDepartment().equals(manager.getDepartment())) {
                this.addReports(manager);
                System.out.println("LOG: new Employee hired (" + manager.getName() + ", " + manager.getDepartment() + ", "
                + manager.getTitle() + ")");
            } else {
                throw new Exception("ERROR: cannot hire an Employee who is not a direct or indirect report.");
            }
        } else {
            throw new Exception("ERROR: cannot hire an Employee of an equal or greater tier.");
        }
    }

    public void hire(Employee employee) throws Exception {
        if (this.getTier() > employee.getTier()) {
            if (this.getDepartment().equals(employee.getDepartment())) {
                this.addReports(employee);
                System.out.println("LOG: new Employee hired (" + employee.getName() + ", " + employee.getDepartment() + ", "
                        + employee.getTitle() + ")");
            } else {
                throw new Exception("ERROR: cannot hire an Employee who is not a direct or indirect report.");
            }
        } else {
            throw new Exception("ERROR: cannot hire an Employee of an equal or greater tier.");
        }
    }

    public void fire(Manager manager) throws Exception {
        if (this.getTier() > manager.getTier()) {
            if (this.getDepartment().equals(manager.getDepartment())) {
                System.out.println("LOG: existing Employee fired (" + manager.getName() + ", " + manager.getDepartment() + ", "
                        + manager.getTitle() + ")");
                this.getReports().remove(manager);
                if (manager.getReports() != null) {
                    System.out.print("LOG: reports re-assigned [");
                    int count = manager.getReports().size();
                    for (Employee e : manager.getReports()) {
                        count--;
                        e.setTier(e.getTier() + 1);
                        Manager m = new Manager(e.getSalary(), 0, e.getName(), e.getDepartment(), e.getTitle(), new ArrayList<>(), e.getTier() + 1);
                        System.out.print(m.getName() + ", " + m.getDepartment() + ", " + m.getTitle());
                        if (count > 0) {
                             System.out.print(", ");
                        }
                        this.getReports().add(m);
                    }
                    System.out.println("]");
                }
            } else {
                throw new Exception("ERROR: cannot fire an Employee who is not a direct or indirect report.");
            }
        } else {
            throw new Exception("ERROR: cannot fire an Employee of an equal or greater tier.");
        }
    }

    public void fire(Employee employee) throws Exception {
        if (this.getTier() > employee.getTier()) {
            if (this.getDepartment().equals(employee.getDepartment())) {
                System.out.println("LOG: existing Employee fired (" + employee.getName() + ", " + employee.getDepartment() + ", "
                        + employee.getTitle() + ")");
                this.getReports().remove(employee);
            } else {
                throw new Exception("ERROR: cannot fire an Employee who is not a direct or indirect report.");
            }
        } else {
            throw new Exception("ERROR: cannot fire an Employee of an equal or greater tier.");
        }
    }
}
