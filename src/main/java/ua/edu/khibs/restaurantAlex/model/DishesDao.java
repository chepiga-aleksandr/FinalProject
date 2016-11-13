package ua.edu.khibs.restaurantAlex.model;

import java.util.List;

public interface DishesDao {

    Dishes load(int id);

    List<Dishes> getAll();
}
