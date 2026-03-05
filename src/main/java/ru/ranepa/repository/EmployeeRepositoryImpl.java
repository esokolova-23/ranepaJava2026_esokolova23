package ru.ranepa.repository;

import ru.ranepa.model.Employee;
import java.util.*;

public class EmployeeRepositoryImpl implements EmployeeRepository {
    private final Map<Long, Employee> employees = new HashMap<>();
    private static long nextId = 1;

    @Override
    public String save(Employee employee) {
        if (employee == null) {
            return "Oshibka: sotrudnik ne mozhet byt' null";
        }
        if (employee.getId() == 0) {
            employee.setId(nextId++);
        }
        employees.put(employee.getId(), employee);
        return "Sotrudnik " + employee.getId() + " " + employee.getName() + " dobavlen/obnovlyon";
    }

    @Override
    public Optional<Employee> findById(long id) {
        return Optional.ofNullable(employees.get(id));
    }

    @Override
    public Iterable<Employee> findAll() {
        return employees.values();
    }

    @Override
    public String delete(long id) {
        Employee removed = employees.remove(id);
        return (removed != null) ? "Sotrudnik " + id + " udalen" : "Sotrudnik s ID " + id + " ne nayden"; // Сотрудник удалён / не найден
    }
}

