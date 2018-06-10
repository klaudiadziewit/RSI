package payment;

import java.io.*;

public class Bill implements IPrintable {
    private double mealPrice;
    private double drinkPrice;
    private double totalPrice;

    public Bill(double mealPrice, double drinkPrice, double totalPrice) {
        this.mealPrice = mealPrice;
        this.drinkPrice = drinkPrice;
        this.totalPrice = totalPrice;
    }

    @Override
    public void print() {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("bill.txt", "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        assert writer != null;
        writer.println("JUSEPPE TRATTORIA\n");
        writer.println("Meal price: " + mealPrice);
        writer.println("Drink price: " + drinkPrice);
        writer.println("Total price: " + totalPrice);
        writer.close();
    }
}
