package ua.edu.khibs.restaurantAlex.model;

import java.util.List;

public interface EmployeesDao {

    Employees add();

    Employees remove();

    Employees find();

    List<Employees> getAll();
}
