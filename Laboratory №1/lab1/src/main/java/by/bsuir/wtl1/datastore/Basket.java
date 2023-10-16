package by.bsuir.wtl1.datastore;

import java.util.ArrayList;

public class Basket {
    private ArrayList<Ball> ballsInBasket;
    private int basketCapacity;

    public Basket(){
        ballsInBasket = new ArrayList<>();
        basketCapacity = 50;
    }

    public Basket(ArrayList<Ball> balls){
        this.ballsInBasket = balls;
        this.basketCapacity = 50;
    }

    public Basket(int capacity){
        this.ballsInBasket = new ArrayList<>();
        this.basketCapacity = capacity;
    }

    public Basket(ArrayList<Ball> balls,int capacity){
        this.ballsInBasket = balls;
        this.basketCapacity = capacity;
    }

    public int getBallsWeight(){
        int totalWeight = 0;
        for (Ball ball : ballsInBasket) {
            totalWeight += ball.getBallWeight();
        }
        return totalWeight;
    }

    public int getSpecifiedColorBallsWeight(Color color){
        int totalWeightOfColoredBalls = 0;
        for(Ball ball : ballsInBasket){
            if(ball.getBallColor().getColorCode()
               == color.getColorCode()) {
                totalWeightOfColoredBalls += ball.getBallWeight();
            }
        }
        return totalWeightOfColoredBalls;
    }

    public int getAmountOfBalls(){
        return ballsInBasket.size();
    }

    public int getAmountOfColoredBalls(Color color){
        ArrayList<Ball> coloredBallsInBasket = new ArrayList<>();
        for (Ball ball : ballsInBasket) {
            if (ball.getBallColor().getColorCode()
                == color.getColorCode()) {
                coloredBallsInBasket.add(ball);
            }
        }
        return coloredBallsInBasket.size();
    }

    public boolean addBall(Ball ball){
        if (!(getBallsWeight() + ball.getBallWeight()
            > basketCapacity)) {
            return ballsInBasket.add(ball);
        }
        return false;
    }

    public boolean removeBall(Ball ball) {
        if (ballsInBasket.contains(ball)) {
            return ballsInBasket.remove(ball);
        }
        return false;
    }
    public boolean removeBall(int index) {
        if (ballsInBasket.size() > index){
            return ballsInBasket.remove(ballsInBasket.get(index));
        }
        return false;
    }
    public void clear(){
        ballsInBasket.clear();
    }

    @Override
    public boolean equals(Object object){
        if (object == this) {
            return true;
        } else if (object == null) {
            return false;
        } else if (getClass() != object.getClass()) {
            return false;
        } else {
            boolean result;
            Basket comparableBasket = (Basket) object;
            result = this.basketCapacity == comparableBasket.basketCapacity
                     && this.ballsInBasket.equals(comparableBasket.ballsInBasket);
            return result;
        }
    }
    @Override
    public int hashCode(){
        int hash = 23;
        hash *= (basketCapacity != 0 ? basketCapacity : 1);
        hash += (ballsInBasket != null ? ballsInBasket.hashCode() : 0);
        return hash;
    }
    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass())
                .append("{")
                .append("basketCapacity: ")
                .append(this.basketCapacity)
                .append(", ballsInBasket: ")
                .append(this.ballsInBasket.toString())
                .append("}");
        return stringBuilder.toString();
    }
}
