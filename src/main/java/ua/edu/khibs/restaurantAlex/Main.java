package ua.edu.khibs.restaurantAlex;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.edu.khibs.restaurantAlex.model.EmployeesDao;
import ua.edu.khibs.restaurantAlex.model.jdbc.JdbcDishesDao;
import ua.edu.khibs.restaurantAlex.model.DishesDao;
import ua.edu.khibs.restaurantAlex.model.jdbc.JdbcEmployeesDao;

//import java.util.Scanner;

public class Main {

//    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

//    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");

        DishesDao dao = context.getBean(DishesDao.class);

        System.out.println(dao.load(3));
//      dishesDao.getAll();

        //System.out.println(dishesDao.load(3));

        //System.out.println("All dishes");
       //dishesDao.getAll().forEach(System.out::println);

//        System.out.println("Сделайте свой выбор:\n\nСОТРУДНИКИ:\n\n1. Добавить сотрудника\n" +
//                "2. Удалить сотрудника\n" +
//                "3. Поиск сотрудника по имени\n" +
//                "4. Вывести на экран список всех сотрудников\n" +
//                "БЛЮДА:\n\n5.Добавить новое блюдо\n" +
//                "6.Удалить блюдо\n" +
//                "7.Найти блюдо по названию\n" +
//                "8.Вывести список всех блюд\n");
//
//        String nextLine = scanner.nextLine();
//        System.out.println(nextLine);
//
//        switch (nextLine) {
//            case "1":
//            case "2":
//            case "3":
//            case "4":
//                EmployeesDao employeesDao = new JdbcEmployeesDao();
//                System.out.println("All employees");
//                employeesDao.getAll().forEach(System.out::println);
//            case "5":
//            case "6":
//            case "7":
//
//                System.out.println("ua.edu.khibs.restaurantAlex.model.Dishes with ID=3");
//                System.out.println(dishesDao.load(3));
//            case "8":
//                System.out.println("All dishes");
//                dishesDao.getAll().forEach(System.out::println);
//        }
  }
}
