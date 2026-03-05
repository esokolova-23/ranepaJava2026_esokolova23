package ru.ranepa.servise;

import ru.ranepa.model.Employee;
import ru.ranepa.repository.EmployeeRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HRMService {
    private final EmployeeRepository repository;

    public HRMService(EmployeeRepository repository) {
        this.repository = repository;
    }


    public Employee addEmployee(String position, String name, double salary) {
        Employee employee = new Employee(position, name, salary, LocalDate.now());
        repository.save(employee);
        return employee;
    }


    public List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<>();
        for (Employee emp : repository.findAll()) {
            list.add(emp);
        }
        return list;
    }

    // Поиск сотрудника по ID
    public Optional<Employee> getEmployeeById(long id) {
        return repository.findById(id);
    }

    // Удаление сотрудника по ID
    public boolean deleteEmployee(long id) {
        String result = repository.delete(id);

        return !result.isEmpty();
    }

    // Расчёт средней зарплаты
    public double calculateAverageSalary() {
        List<Employee> employees = getAllEmployees();
        if (employees.isEmpty()) {
            return 0.0;
        }
        BigDecimal total = BigDecimal.ZERO;
        for (Employee emp : employees) {
            total = total.add(emp.getSalary());
        }

        return total.divide(BigDecimal.valueOf(employees.size()), 2, RoundingMode.HALF_UP).doubleValue();
    }


    public Optional<Employee> findHighestPaidEmployee() {
        List<Employee> employees = getAllEmployees();
        if (employees.isEmpty()) {
            return Optional.empty();
        }
        Employee highest = employees.get(0);
        for (Employee emp : employees) {
            if (emp.getSalary().compareTo(highest.getSalary()) > 0) {
                highest = emp;
            }
        }
        return Optional.of(highest);
    }


    public List<Employee> filterByPosition(String position) {
        List<Employee> result = new ArrayList<>();
        for (Employee emp : getAllEmployees()) {
            if (emp.getPosition().equalsIgnoreCase(position)) {
                result.add(emp);
            }
        }
        return result;
    }


    public String getStatistics() {
        List<Employee> employees = getAllEmployees();
        int count = employees.size();
        double averageSalary = calculateAverageSalary();
        Optional<Employee> highestPaid = findHighestPaidEmployee();

        StringBuilder stats = new StringBuilder();
        stats.append("Stdtistika kompanii ");
        stats.append(String.format("vsego sonrudnikov: %d\n", count));
        stats.append(String.format("srednya zp: %.2f\n", averageSalary));

        if (highestPaid.isPresent()) {
            Employee emp = highestPaid.get();
            stats.append(String.format("samaia visokooplachivaemaia : %s (%.2f)\n",
                    emp.getName(), emp.getSalary().doubleValue()));
        } else {
            stats.append("samaia visokooplachivaemai: net dannih");
        }
        return stats.toString();
    }
}
