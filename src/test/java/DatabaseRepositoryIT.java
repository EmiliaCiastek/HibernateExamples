import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

public class DatabaseRepositoryIT {

    private DatabaseRepository databaseRepository;

    //TODO: add testCases -> few elements should be added to database for some tests

    @BeforeMethod
    public void setUp() {
        DatabaseRepository.openConnection();
        databaseRepository = new DatabaseRepository();
    }

    @AfterMethod
    public void closeConnection() {
        DatabaseRepository.closeConnection();
    }

    @Test
    public void should_return_empty_collection_when_getAll() {
        Collection<Employee> actual = databaseRepository.getAll();

        assertThat(actual).isEmpty();
    }

    @Test
    public void should_return_collection_with_one_element_when_add_and_getAll() {
        Employee employee = new Employee("employee name", 5000.0);
        databaseRepository.add(employee);

        Collection<Employee> actual = databaseRepository.getAll();

        int expectedSize = 1;
        assertThat(actual.size()).isEqualTo(expectedSize);
    }

    @Test
    public void should_add_collection_to_database_when_addAll() {
        Employee firstEmployee = new Employee("First", 1000.0);
        Employee secondEmployee = new Employee("Second", 2000.0);
        Employee thirdEmployee = new Employee("Third", 3000.0);
        Collection<Employee> employeesToAdd = Arrays.asList(firstEmployee, secondEmployee, thirdEmployee);

        databaseRepository.addAll(employeesToAdd);
        Collection<Employee> actual = databaseRepository.getAll();

        assertThat(actual.size()).isEqualTo(employeesToAdd.size());
        //assertThat(actual).containsExactlyInAnyOrder(firstEmployee, secondEmployee, thirdEmployee); TODO: override equals and hashCode
    }

    @Test
    public void should_return_element_with_the_same_id_as_in_query() {
        Employee firstEmployee = new Employee("First", 1000.0);
        Employee secondEmployee = new Employee("Second", 2000.0);
        Employee thirdEmployee = new Employee("Third", 3000.0);
        Collection<Employee> employeesToAdd = Arrays.asList(firstEmployee, secondEmployee, thirdEmployee);

        databaseRepository.addAll(employeesToAdd);

        int idToFound = 2;

        Employee found = databaseRepository.getById(idToFound);

        SoftAssertions soft = new SoftAssertions();
        soft.assertThat(found.getId()).isEqualTo(idToFound);
        soft.assertThat(found.getFirstName()).isEqualTo(secondEmployee.getFirstName());
        soft.assertThat(found.getSalary()).isEqualTo(secondEmployee.getSalary());
        soft.assertAll();
    }

    @Test
    public void should_update_element_with_provided_id(){
        Employee firstEmployee = new Employee("First", 1000.0);
        Employee secondEmployee = new Employee("Second", 2000.0);
        Collection<Employee> employeesToAdd = Arrays.asList(firstEmployee, secondEmployee);
        databaseRepository.addAll(employeesToAdd);

        int idToUpdate = 2;

        Employee updatedEmployee = new Employee(idToUpdate, "Second", 3000.0);
        databaseRepository.updateById(updatedEmployee); //TODO: what if there is no such object in table?

        Employee found = databaseRepository.getById(idToUpdate);

        SoftAssertions soft = new SoftAssertions();
        soft.assertThat(found.getId()).isEqualTo(idToUpdate);
        soft.assertThat(found.getFirstName()).isEqualTo(updatedEmployee.getFirstName());
        soft.assertThat(found.getSalary()).isEqualTo(updatedEmployee.getSalary());
        soft.assertAll();
    }

    @Test
    public void should_remove_element_with_provided_id(){
        Employee firstEmployee = new Employee("First", 1000.0);
        databaseRepository.add(firstEmployee);

        databaseRepository.removeById(1);

        assertThat(databaseRepository.getAll()).isEmpty();
    }
}