import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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

//  @JoinColumn(name = "department")
//  @ManyToOne
//  private Department department;

  public Employee() {
  }

  public Employee(String firstName, double salary) {
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

  //  public Department getDepartment() {
//    return department;
//  }
//
//  public void setDepartment(Department department) {
//    this.department = department;
//  }
}
