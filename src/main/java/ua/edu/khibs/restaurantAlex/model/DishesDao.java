package ua.edu.khibs.restaurantAlex.model;

import java.util.List;

public interface DishesDao {

    Dishes addDishes(Dishes dishes);
    void removeDishes(String name);
    Dishes findDishesByName(String name);
    List<Dishes> getAll();
}