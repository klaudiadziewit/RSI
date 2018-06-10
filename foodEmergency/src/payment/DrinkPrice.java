package payment;

public class DrinkPrice {
    private double value;
    private double[][] prices = new double[][]{{1, 2, 3, 4},
            {3.5, 4.5, 4.5, 2.0}};

    public double getPrice(int p) {
        if(p == 0){
            value = 0.0;
        }
        else if(p == 1){
            value = prices[1][0];
        }
        else if (p==2){
            value = prices[1][1];
        }
        else if (p==3){
            value = prices[1][2];
        }
        else if (p==4){
            value = prices[1][3];
        }
        return value;
    }
}
