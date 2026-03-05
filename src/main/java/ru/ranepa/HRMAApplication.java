package ru.ranepa;

import ru.ranepa.model.Employee;

import java.time.LocalDate;

public class HRMAApplication {
    public static void main(String[] args) {
        var employee = new Employee("director","Ivanov Vitaliy Sergeevich ",25_000.0, LocalDate.of(2026,2,26));
        System.out.println(employee);
    }
}
