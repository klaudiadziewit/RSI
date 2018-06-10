package menu;

public class Meal {
    private int mealId;
    private Double price;

    public Meal(int mealId, Double price) {
        this.mealId = mealId;
        this.price = price;
    }

    public int getMealId() {
        return mealId;
    }
    public void setMealId(int mealId) {
        this.mealId = mealId;
    }

    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
}
