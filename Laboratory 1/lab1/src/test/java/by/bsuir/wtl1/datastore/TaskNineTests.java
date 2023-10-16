package by.bsuir.wtl1.datastore;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class TaskNineTests {
    @Test
    public void ninthTaskCheck() {
        Basket testBasket = new Basket(100);
        BlueColor blue = new BlueColor();
        RedColor red = new RedColor();
        for (int i = 0; i < 5; i++) {
            Ball newBall = new Ball(blue);
            testBasket.addBall(newBall);
        }
        for (int i = 0; i < 5; i++) {
            Ball newBall = new Ball(red);
            testBasket.addBall(newBall);
        }
        assertEquals(5, testBasket.getAmountOfColoredBalls(blue));
        assertEquals(100, testBasket.getBallsWeight());
        testBasket.removeBall(0);
        testBasket.removeBall(1);
        assertEquals(3, testBasket.getAmountOfColoredBalls(blue));
        assertEquals(80, testBasket.getBallsWeight());
        assertEquals(8, testBasket.getAmountOfBalls());
        testBasket.addBall(new Ball(blue));
        assertEquals(4, testBasket.getAmountOfColoredBalls(blue));
        assertEquals(40, testBasket.getSpecifiedColorBallsWeight(blue));
        Ball extraBall = new Ball(blue);
        testBasket.addBall(extraBall);
        assertEquals(5, testBasket.getAmountOfColoredBalls(blue));
        assertEquals(50, testBasket.getSpecifiedColorBallsWeight(blue));
        testBasket.removeBall(extraBall);
        assertEquals(4, testBasket.getAmountOfColoredBalls(blue));
        assertEquals(40, testBasket.getSpecifiedColorBallsWeight(blue));
        testBasket.clear();
        assertFalse(testBasket.removeBall(0));
        assertEquals(0, testBasket.getAmountOfBalls());
    }
}
