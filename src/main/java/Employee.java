import javax.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "salary")
    private double salary;

    public Employee() {
    }

    public Employee(String firstName, double salary) {
        this.firstName = firstName;
        this.salary = salary;
    }

    public Employee(int id, String firstName, double salary) {
        this.id = id;
        this.firstName = firstName;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", salary=" + salary +
                '}';
    }

    //TODO: add equals and hashCode
    //TODO: add Date
}
