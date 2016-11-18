package ua.edu.khibs.restaurantAlex.model;

public class Menu {

    private int ID;
    private String name;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                '}';
    }
}
