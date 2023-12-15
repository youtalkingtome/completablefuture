package reducevscollect;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Setter
@Getter
@AllArgsConstructor
public class RedcueDemo {
    public void main(String args[])
    {
        //Adding the Elements of a List.

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        int sum = numbers.stream()
                .reduce(0, (a, b) -> a + b);
        System.out.println("Sum: " + sum); // Output: Sum: 15

        //Multiply Elements in a List.
        List<Integer> numbers2 = Arrays.asList(1, 2, 3, 4, 5);
        int product = numbers2.stream()
                .reduce(1, (a, b) -> a * b);
        System.out.println("Product: " + product); // Output: Product: 120

        //Finding the Maximum Value
        List<Integer> numbers3 = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> max = numbers.stream()
                .reduce(Integer::max);
        max.ifPresent(value -> System.out.println("Max: " + value)); // Output: Max: 5

        //String Concatenation

        List<String> words = Arrays.asList("Java", "Stream", "API");
        String concatenated = words.stream()
                .reduce("", (a, b) -> a + b);
        System.out.println("Concatenated String: " + concatenated); // Output: Concatenated String: JavaStreamAPI

        Employee e1=new Employee("IT", 10000, false);
        Employee e2=new Employee("Commerce", 2000, true);
        Employee e3=new Employee("Infra", 30000, true);
        Employee e4=new Employee("Business", 40000, true);

        List<Employee> employees=new LinkedList<Employee>();
        employees.add(e1);
        employees.add(e2);
        employees.add(e3);
        employees.add(e4);

        final double SALARY_THRESHOLD = 100000.0;
        final double DISCOUNT_FACTOR = 0.9; // 10% discount

        double totalSalary = employees.stream()
                // Step 1: Filter to include only full-time employees
                .filter(Employee::isFullTime)
                // Step 2 & 3: Group by department and sum the salaries
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.summingDouble(Employee::getSalary)))
                .entrySet().stream()
                // Step 4: Apply discount if salary exceeds threshold
                .map(entry -> entry.getValue() > SALARY_THRESHOLD ?
                        entry.getValue() * DISCOUNT_FACTOR : entry.getValue())
                // Step 5: Sum the totals
                .reduce(0.0, Double::sum);

        System.out.println("Total Salary Expenditure: " + totalSalary);

    }

    @Getter
    @Setter
    @AllArgsConstructor
    class Employee {
        private String department;
        private double salary;
        private boolean isFullTime;
    }
}
