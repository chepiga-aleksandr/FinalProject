package ua.edu.khibs.restaurantAlex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.edu.khibs.restaurantAlex.model.Dishes;
import ua.edu.khibs.restaurantAlex.model.DishesDao;
import ua.edu.khibs.restaurantAlex.model.EmployeesDao;

import java.util.Scanner;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");

        DishesDao dishesDao = context.getBean(DishesDao.class);
        EmployeesDao employeesDao = context.getBean(EmployeesDao.class);

        System.out.println("\nСделайте свой выбор:\n\nСОТРУДНИКИ:\n\n1. Добавить сотрудника\n" +
                "2. Удалить сотрудника\n" +
                "3. Поиск сотрудника по имени\n" +
                "4. Вывести на экран список всех сотрудников\n\n" +
                "БЛЮДА:\n\n5.Добавить новое блюдо\n" +
                "6.Удалить блюдо\n" +
                "7.Найти блюдо по названию\n" +
                "8.Вывести список всех блюд\n");

        String sw = scanner.nextLine();
        System.out.println(sw);

        switch (sw) {
            case "1":
                System.out.println("Список ДО добавления");
                employeesDao.getAll().forEach(System.out::println);
                System.out.println("Введите имя добавляемого чувака/чувихи");
                System.out.println(employeesDao.addEmployee(scanner.nextLine()));
                System.out.println("Список ПОСЛЕ добавления");
                employeesDao.getAll().forEach(System.out::println);
                break;
            case "2":
                System.out.println("Список ДО удаления");
                employeesDao.getAll().forEach(System.out::println);
                System.out.println("Введите имя удаляемого чувака/чувихи");
                employeesDao.remove(scanner.nextLine());
                System.out.println("Список ПОСЛЕ удаления");
                employeesDao.getAll().forEach(System.out::println);
                break;
            case "3":
                System.out.println("Введите имя искомого чувака/чувихи");
                System.out.println(employeesDao.findEmployeeByName(scanner.nextLine()));
                break;
            case "4":
                System.out.println("All employees");
                employeesDao.getAll().forEach(System.out::println);
                break;
            case "5":

                System.out.println("Введите название нового блюда:");
                String name = scanner.next();
                System.out.println("Введите вес блюда:");
                int weight = scanner.nextInt();
                System.out.println("Введите стоимость блюда:");
                int price = scanner.nextInt();

                Dishes dishes = new Dishes(name, weight, price);
                dishesDao.addDishes(dishes);

                break;
            case "6":
                break;
            case "7":
                System.out.println("ua.edu.khibs.restaurantAlex.model.Dishes with ID=3");
//                System.out.println(dishesDao.load(3));
                break;
            case "8":
                System.out.println("All dishes");
                dishesDao.getAll().forEach(System.out::println);
                break;
        }
    }
}
