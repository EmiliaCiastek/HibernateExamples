import java.util.Collection;

public interface Repository {
    void add(Employee employee);

    void addAll(Collection<Employee> employees);

    Collection<Employee> getAll();

    Employee getById(int id);

    void updateById(Employee employee);

    void removeById(int id);
}
