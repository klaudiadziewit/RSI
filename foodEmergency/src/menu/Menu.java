package menu;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

public class Menu implements Serializable {
    Map<Integer, Double> MenuMap = new TreeMap<>();
    private Meal meal;
    private Meal meal1;
    private Meal meal2;
    private Meal meal3;
    private Meal meal4;
    private Meal meal5;
    private Meal meal6;
    private Meal meal7;
    private Meal meal8;
    private Meal meal9;
    private Meal meal10;

    public Menu() {
        meal = new Meal(0, 15.5);
        meal1 = new Meal(1, 16.5);
        meal2 = new Meal(2, 24.0);
        meal3 = new Meal(3, 25.0);
        meal4 = new Meal(4, 22.0);
        meal5 = new Meal(5, 27.0);
        meal6 = new Meal(6, 15.0);
        meal7 = new Meal(7, 18.5);
        meal8 = new Meal(8, 19.0);
        meal9 = new Meal(9, 20.0);
        meal10 = new Meal(10, 22.5);
    }

    public void initializeMap(){
        MenuMap.put(meal.getMealId(), meal.getPrice());
        MenuMap.put(meal1.getMealId(), meal1.getPrice());
        MenuMap.put(meal2.getMealId(), meal2.getPrice());
        MenuMap.put(meal3.getMealId(), meal3.getPrice());
        MenuMap.put(meal4.getMealId(), meal4.getPrice());
        MenuMap.put(meal5.getMealId(), meal5.getPrice());
        MenuMap.put(meal6.getMealId(), meal6.getPrice());
        MenuMap.put(meal7.getMealId(), meal7.getPrice());
        MenuMap.put(meal8.getMealId(), meal8.getPrice());
        MenuMap.put(meal9.getMealId(), meal9.getPrice());
        MenuMap.put(meal10.getMealId(), meal10.getPrice());
    }

    public double getPrice(int key){
        initializeMap();
        double price = MenuMap.get(key);
        return price;
    }
}

