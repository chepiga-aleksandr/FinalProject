package ua.edu.khibs.restaurantAlex;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTest {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml", "hibenate-context.xml");

        MainTest mainTest = context.getBean(MainTest.class);
    }
}
