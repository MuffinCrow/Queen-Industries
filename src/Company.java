import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Company {

    public static final int EMPLOYEE = 1;
    public static final int MANAGER = 2;
    public static final int DIRECTOR = 3;
    public static final double SHARE_PRICE = 123.45;

    private String name;
    private List<Employee> staff;
    private static Company company;

    /**
     * Singleton constructor pattern. DO NOT MODIFY.
     *
     * @return a singleton instance of the Company class
     * @throws Exception
     */
    public static Company getInstance() throws Exception {
        if (company == null) {
            company = new Company("Queen Industries");
        }

        return company;
    }

    /*
     * Private constructor implementing the singleton pattern. DO NOT MODIFY.
     */
    private Company(String name) throws Exception {
        this.name = name;
        this.staff = new ArrayList<>();

        staff.add(new Director(145000, 1000, 10000, "Laurel Lance", "Marketing", "Director of Marketing", new ArrayList<>(), Company.DIRECTOR));
        staff.add(new Director(215000, 2500, 15000, "Felicity Smoak", "Engineering", "Director of Engineering", new ArrayList<>(), Company.DIRECTOR));
        staff.add(new Director(1650000, 1500, 25000, "Oliver Queen", "Sales", "Director of Sales", new ArrayList<>(), Company.DIRECTOR));
    }

    /**
     * Staffs the Engineering department. DO NOT MODIFY.
     *
     * @throws Exception
     */
    public void staffEngineeringDepartment() throws Exception {
        Director director = this.getDirector("Engineering");

        if (director != null) {
            Manager qaManager = new Manager(155000, 7500, "Sara Lance", "Engineering", "QA Manager", new ArrayList<>(), Company.MANAGER);
            Manager devManager = new Manager(185000, 10000, "Curtis Holt", "Engineering", "Development Manager", new ArrayList<>(), Company.MANAGER);

            director.hire(qaManager);
            director.hire(devManager);

            Employee qaEmployee1 = new Employee(110000, "Moira Queen", "Engineering", "QA Analyst III");
            Employee qaEmployee2 = new Employee(95000, "Quentin Lance", "Engineering", "QA Analyst II");
            Employee qaEmployee3 = new Employee(80000, "Slade Wilson", "Engineering", "QA Analyst I");

            qaManager.hire(qaEmployee1);
            qaManager.hire(qaEmployee2);
            qaManager.hire(qaEmployee3);

            Employee devEmployee1 = new Employee(125000, "Adrian Chase", "Engineering", "QA Analyst III");
            Employee devEmployee2 = new Employee(105000, "Rene Ramirez", "Engineering", "QA Analyst II");
            Employee devEmployee3 = new Employee(85000, "Dinah Drake", "Engineering", "QA Analyst I");

            devManager.hire(devEmployee1);
            devManager.hire(devEmployee2);
            devManager.hire(devEmployee3);
        }
    }

    /**
     * Staffs the Marketing department. DO NOT MODIFY.
     *
     * @throws Exception
     */
    public void staffMarketingDepartment() throws Exception {
        Director director = this.getDirector("Marketing");

        if (director != null) {
            Manager socialManager = new Manager(115000, 5000, "Malcolm Merlyn", "Marketing", "Social Media Manager", new ArrayList<>(), Company.MANAGER);

            director.hire(socialManager);

            Employee socialEmployee1 = new Employee(85000, "Thea Queen", "Marketing", "Social Media Analyst III");
            Employee socialEmployee2 = new Employee(65000, "Tommy Merlyn", "Marketing", "Social Media Analyst II");
            Employee socialEmployee3 = new Employee(45000, "Roy Harper", "Marketing", "Social Media Analyst I");

            socialManager.hire(socialEmployee1);
            socialManager.hire(socialEmployee2);
            socialManager.hire(socialEmployee3);
        }
    }

    /**
     * Staffs the Sales department. DO NOT MODIFY.
     *
     * @throws Exception
     */
    public void staffSalesDepartment() throws Exception {
        Director director = this.getDirector("Sales");

        if (director != null) {
            Manager insideSalesManager = new Manager(115000, 15000, "Ricardo Diaz", "Sales", "Inside Sales Manager", new ArrayList<>(), Company.MANAGER);
            Manager outsideSalesManager = new Manager(125000, 25000, "Mar Novu", "Sales", "Outside Sales Manager", new ArrayList<>(), Company.MANAGER);

            director.hire(insideSalesManager);
            director.hire(outsideSalesManager);

            Employee insideSalesEmployee1 = new Employee(105000, "Emilko Queen", "Sales", "Inside Sales Reprsentative III");
            Employee insideSalesEmployee2 = new Employee(90000, "Mia Smoak", "Sales", "Inside Sales Representative II");

            insideSalesManager.hire(insideSalesEmployee1);
            insideSalesManager.hire(insideSalesEmployee2);

            Employee outsideSalesEmployee1 = new Employee(110000, "William Clayton", "Sales", "Outside Sales Representative III");
            Employee outsideSalesEmployee2 = new Employee(95000, "Connor Hawke", "Sales", "Outside Sales Representative II");

            outsideSalesManager.hire(outsideSalesEmployee1);
            outsideSalesManager.hire(outsideSalesEmployee2);
        }
    }

    /**
     * Reduces the staff of the Sales department. DO NOT MODIFY.
     */
    public void reduceSalesStaff() throws Exception {
        Director director = this.getDirector("Sales");
        Manager salesManager = (Manager) this.getEmployee("Mar Novu");

        Manager marketingManager = (Manager) this.getEmployee("Malcolm Merlyn");
        Employee employee = this.getEmployee("Roy Harper");

        director.fire(salesManager);
        marketingManager.fire(employee);
    }

    /**
     * Adjust the salaries of Employees. DO NOT MODIFY.
     *
     * @throws Exception
     */
    public void adjustSalaries() throws Exception {
        Director director = this.getDirector("Sales");
        Employee toGetRaise = this.getEmployee("Emilko Queen");
        Employee toGetReduction = this.getEmployee("Mia Smoak");
        Manager toAlsoGetRaise = (Manager) this.getEmployee("Ricardo Diaz");

        this.printEmployeeDetails(toGetRaise);
        director.adjustSalary(7500, toGetRaise);
        this.printEmployeeDetails(toGetRaise);

        this.printEmployeeDetails(toGetReduction);
        director.adjustSalary(-5000, toGetReduction);
        this.printEmployeeDetails(toGetReduction);

        this.printEmployeeDetails(toAlsoGetRaise);
        director.adjustSalary(25000, toAlsoGetRaise);
        this.printEmployeeDetails(toAlsoGetRaise);
    }

    /**
     * Verifies that invalid operations fail as expected. DO NOT MODIFY.
     */
    public void testInvalidOperations() {
        Director director1 = this.getDirector("Marketing");
        Director director2 = this.getDirector("Sales");
        Manager manager1 = (Manager) this.getEmployee("Sara Lance");
        Manager manager2 = (Manager) this.getEmployee("Ricardo Diaz");

        try {
            director1.fire(manager1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            director1.fire(director2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            Director director = new Director(100000, 0, 0, "Invalid Hire", "Human Resources", "Director of Human Resources", new ArrayList<>(), Company.DIRECTOR);
            manager1.hire(director);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            Director director = new Director(100000, 0, 0, "Invalid Hire", "Human Resources", "Director of Human Resources", new ArrayList<>(Arrays.asList(new Employee[] { director1, director2 })), Company.DIRECTOR);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            director1.adjustSalary(10000, manager2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void printOrganizationChart() {
        System.out.println("\n" + this.name + " Organization Chart");
        for (Employee d : this.staff) {
            System.out.println(" - " + d.getName() + ", " + d.getTitle());
            if (((Manager) d).getReports() != null) {
                for (Employee m : ((Manager) d).getReports()) {
                    System.out.println("    - " + m.getName() + ", " + m.getTitle());
                    if (((Manager) m).getReports() != null) {
                        for (Employee e : ((Manager) m).getReports()) {
                            System.out.println("       - " + e.getName() + ", " + e.getTitle());
                        }
                    }
                }
            }
        }
        System.out.print("\n");
    }

    private Director getDirector(String department) {
        for (Employee d : this.staff) {
            if (d.getDepartment().equals(department)) {
                return ((Director) d);
            }
        }
        return null;
    }

    private Employee getEmployee(String name) {
        for (Employee d : this.staff) {
            if (d.getName().equals(name)) {
                return d;
            } else if (((Manager) d).getReports() != null) {
                for (Employee m : ((Manager) d).getReports()) {
                    if (m.getName().equals(name)) {
                        return m;
                    } else if (((Manager) m).getReports() != null) {
                        for (Employee e : ((Manager) m).getReports()) {
                            if (e.getName().equals(name)) {
                                return e;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    private void printEmployeeDetails(Employee e) {
        System.out.println("Employee     : " + e.getName());
        System.out.println("Department   : " + e.getDepartment());
        System.out.println("Title        : " + e.getTitle());
        switch (e.getTier()) {
            case 1:
                System.out.println("Compensation : " + Double.valueOf(e.getSalary()) + "\n");
                break;
            case 2:
                System.out.println("Compensation : " + (Double.valueOf(e.getSalary()) + Double.valueOf(((Manager) e).getBonus())) + "\n");
                break;
            case 3:
                System.out.println("Compensation : " + (Double.valueOf(e.getSalary()) + Double.valueOf(((Manager) e).getBonus()) + Double.valueOf(((Director) e).getStock())) + "\n");
                break;
            default:
                System.out.println("Compensation : " + Double.valueOf(e.getSalary()) + "\n");
                break;
        }
    }

    /**
     * Main method executes tests. DO NOT MODIFY.
     *
     * @param args optional command line arguments
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Company company = Company.getInstance();

        company.printOrganizationChart();
        company.staffEngineeringDepartment();
        company.staffMarketingDepartment();
        company.staffSalesDepartment();
        company.printOrganizationChart();
        company.reduceSalesStaff();
        company.printOrganizationChart();
        company.adjustSalaries();
        company.testInvalidOperations();
    }
}
