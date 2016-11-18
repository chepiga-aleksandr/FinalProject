package ua.edu.khibs.restaurantAlex.model;

import java.util.List;

public interface EmployeesDao {

    Employees addEmployee(String name);

    void removeEmployee(String name);

    Employees findEmployeeByName(String name);

    List<Employees> getAll();
}
