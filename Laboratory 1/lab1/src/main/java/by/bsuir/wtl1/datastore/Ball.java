package by.bsuir.wtl1.datastore;

public class Ball {

    private Color ballColor;
    private int ballWeight;

    public Ball() {
       ballColor = new RedColor();
       ballWeight = 10;
    }

    public Ball(Color ballColor) {
        this.ballColor = ballColor;
        ballWeight = 10;
    }

    public Ball(int ballWeight) {
        this.ballColor = new RedColor();
        this.ballWeight = ballWeight;
    }

    public Ball(Color ballColor,int ballWeight) {
        this.ballColor = ballColor;
        this.ballWeight = ballWeight;
    }

    public Color getBallColor() {
        return ballColor;
    }

    public int getBallWeight() {
        return ballWeight;
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
            Ball comparableBall = (Ball) object;
            result = this.ballColor == comparableBall.ballColor
                  && this.ballWeight == comparableBall.ballWeight;
            return result;
        }
    }
    @Override
    public int hashCode(){
        int hash = 31;
        hash *= (ballWeight != 0 ? ballWeight : 1);
        hash += (ballColor != null ? ballColor.hashCode() : 0);
        return hash;
    }
    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass())
                     .append("{")
                     .append("ballWeight: ")
                     .append(this.ballWeight)
                     .append(", ballColor: ")
                     .append(this.ballColor.toString())
                     .append("}");
        return stringBuilder.toString();
    }
}
