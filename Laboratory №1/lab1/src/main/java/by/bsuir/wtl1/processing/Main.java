package by.bsuir.wtl1.processing;

import by.bsuir.wtl1.datastore.Ball;
import by.bsuir.wtl1.datastore.Basket;
import by.bsuir.wtl1.datastore.BlueColor;
import by.bsuir.wtl1.datastore.RedColor;
import by.bsuir.wtl1.datastore.GreenColor;
import by.bsuir.wtl1.io.InputOutputProcess;

public class Main {
    public static void main(String[] args) {
        Basket basket = new Basket(50);
        BlueColor blue = new BlueColor();
        RedColor red = new RedColor();
        GreenColor green = new GreenColor();
        for (int i = 0; i < 5; i++) {
            basket.addBall(new Ball(red,5));
        }
        for (int i = 0; i < 5; i++) {
            basket.addBall(new Ball(green,2));
        }
        for (int i = 0; i < 5; i++) {
            basket.addBall(new Ball(blue,10));
        }
        InputOutputProcess.print("Total balls weight:");
        InputOutputProcess.print(String.valueOf(basket.getBallsWeight()));
        InputOutputProcess.print("Blue balls count:");
        InputOutputProcess.print(String.valueOf(basket.getAmountOfColoredBalls(blue)));
    }
}
