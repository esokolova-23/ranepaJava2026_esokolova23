package ru.ranepa.presentation;

import ru.ranepa.model.Employee;
import ru.ranepa.repository.EmployeeRepository;
import ru.ranepa.repository.EmployeeRepositoryImpl;
import ru.ranepa.servise.HRMService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class HRMApplication {
    private static final Scanner scanner = new Scanner(System.in);
    private static HRMService service;

    public static void main(String[] args) {
        EmployeeRepository repository = new EmployeeRepositoryImpl();
        service = new HRMService(repository);

        while (true) {
            printMenu();
            int choice = readIntInput("Viberete punkt menu: ");
            switch (choice) {
                case 1:
                    listAllEmployees();
                    break;
                case 2:
                    addNewEmployee();
                    break;
                case 3:
                    deleteEmployeeById();
                    break;
                case 4:
                    findEmployeeById();
                    break;
                case 5:
                    showStatistics();
                    break;
                case 6:
                    System.out.println("Vykhod iz programmy. Do svidaniya!");
                    scanner.close();
                    return;
                case 7:
                    filterEmployeesByPosition();
                    break;
                default:
                    System.out.println("Nevernyy punkt menyu. Pozhaluysta, vyberite ot 1 do 7.");
            }

            System.out.println("Nazhmite Enter dlya prodolzheniya");
            scanner.nextLine();
        }
    }

    private static void printMenu() {
        System.out.println(" MENYU UPRAVLENIYA SOTRUDNIKAMI ");
        System.out.println("1. Vydat' spisok vsekh sotrudnikov ");
        System.out.println("2. Dobavit' novogo sotrudnika");
        System.out.println("3. Udalit' sotrudnika po ID");
        System.out.println("4. Poisk sotrudnika po ID");
        System.out.println("5. Pokazat' statistiku (srednyaya zarplata, top-menedzher)");
        System.out.println("6. Vykhod");
        System.out.println("7. Poisk sotrudnikov po dolzhnosti");
        System.out.print("Vash vybor: ");
    }

    private static void listAllEmployees() {
        System.out.println("Spisok vsekh sotrudnikov:");
        List<Employee> employees = service.getAllEmployees();
        if (employees.isEmpty()) {
            System.out.println("Spisok sotrudnikov pust.");
        } else {
            employees.forEach(System.out::println);
        }
    }

    private static void addNewEmployee() {
        System.out.println("Dobavlenie novogo sotrudnika:");
        String name = readStringInput("Vvedite FIO sotrudnika: ");
        String position = readStringInput("Vvedite dolzhnost': ");
        double salary = readDoubleInput("Vvedite zarplatu (naprimer, 25000.00): ");

        Employee added = service.addEmployee(position, name, salary);
        System.out.println("Sotrudnik uspeshno dobavlen: " + added);
    }

    private static void deleteEmployeeById() {
        System.out.println("Udaleniye sotrudnika po ID:");
        long id = readLongInput("Vvedite ID sotrudnika dlya udalenia: ");
        System.out.println(service.deleteEmployee(id));
    }

    private static void findEmployeeById() {
        System.out.println("Poisk sotrudnika po ID:");
        long id = readLongInput("Vvedite ID sotrudnika dlya poiska: ");

        // Поиск сотрудника по ID
        String result = service.getEmployeeById(id);
        System.out.println(result);
    }

    private static void showStatistics() {
        System.out.println("Statistika:");
        String statistics = service.getStatistics();
        System.out.println(statistics);
    }

    private static void filterEmployeesByPosition() {
        System.out.println("Poisk sotrudnikov po dolzhnosti:");
        String position = readStringInput("Vvedite dolzhnost' dlya poiska: ");
        List<Employee> employees = service.filterByPosition(position);
        if (employees.isEmpty()) {
            System.out.println("Sotrudniki s dolzhnost'yu '" + position + "' ne naydeny.");
        } else {
            System.out.println("Sotrudniki s dolzhnost'yu '" + position + "':");
            employees.forEach(System.out::println);
        }
    }

    private static int readIntInput(String prompt) {
        System.out.print(prompt);
        return Integer.parseInt(scanner.nextLine());
    }

    private static String readStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private static double readDoubleInput(String prompt) {
        System.out.print(prompt);
        return Double.parseDouble(scanner.nextLine());
    }

    private static long readLongInput(String prompt) {
        System.out.print(prompt);
        return Long.parseLong(scanner.nextLine());
    }
}