package view;

import exception.WrongInputException;
import menu.Drink;
import menu.Menu;
import payment.Bill;
import payment.DrinkPrice;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, WrongInputException {
        Menu mainMenu = new Menu();
        DrinkPrice drinkPrice = new DrinkPrice();
        Statement statement = new Statement();
        CloseApp close = new CloseApp();
        Drink drink = new Drink();
        drink.createDrinkMenu();

        boolean somethingToDrink;
        String foodChoice;
        String drinkChoice;
        double priceOfMeal;
        double priceOfDrink = 0.0;
        double completePrice = 0.0;
        String content;
        String msg = "Wrong input";

        statement.startingStatement();

        content = new String(Files.readAllBytes(Paths.get("menu.txt")));
        System.out.println(content);

        Scanner readChoice = new Scanner(System.in);
        foodChoice = readChoice.nextLine();
        int fc = Integer.parseInt(foodChoice);
        if (fc < 0 || fc > 10) throw new WrongInputException(msg);

        System.out.println("\nWould you like something to drink? Give a number if YES, give 0 if NO");

        for (int i=0; i<drink.drinks.length; i++) {
            System.out.println(i + 1 + ". " + drink.drinks[i]);
        }

        drinkChoice = readChoice.nextLine();

        switch(drinkChoice){
            case "0":
                System.out.println("You don't want anything to drink");
                break;
            case "1":
                System.out.println("You ordered " + drink.drinks[0]);
                break;
            case "2":
                System.out.println("You ordered " + drink.drinks[1]);
                break;
            case "3":
                System.out.println("You ordered " + drink.drinks[2]);
                break;
            case "4":
                System.out.println("You ordered " + drink.drinks[3]);
                break;
            default:
                System.out.println("There is no such option, your order is cancelled. Goodbye!");
                close.closeApplication();
        }

        priceOfMeal = mainMenu.getPrice(Integer.parseInt(foodChoice));
        priceOfDrink = drinkPrice.getPrice(Integer.parseInt(drinkChoice));
        completePrice = priceOfDrink + priceOfMeal;

        Bill bill = new Bill(priceOfMeal, priceOfDrink, completePrice);

        System.out.println("_____________________________________________");
        System.out.println("_____________________________________________");
        System.out.println("\nYOUR BILL");
        System.out.println("Your meal: " + priceOfMeal);
        System.out.println("Your drink: " + priceOfDrink);
        System.out.println("\nTotal: " + completePrice);

        statement.endingStatement();
        bill.print();
        close.closeApplication();
    }
}
