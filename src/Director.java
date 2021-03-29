import java.util.ArrayList;

public class Director extends Manager {
    int stock;

    public Director(int salary, int stock, int bonus, String name, String department, String title, ArrayList<Employee> reports, int tier) throws Exception {
        super(salary, bonus, name, department, title, reports, tier);
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
