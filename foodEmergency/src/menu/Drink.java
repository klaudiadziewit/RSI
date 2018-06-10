package menu;

public class Drink {
    public String[] drinks;

    public void createDrinkMenu(){
        drinks = new String[]{"Coca-cola", "Fanta", "Sprite", "Water"};
    }

    public String getDrinkName(int indexInDrinksArray){
        createDrinkMenu();
        return drinks[indexInDrinksArray];
    }
}
